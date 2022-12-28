package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.ImgFileUploadUtil;
import com.twy.tripwithyou_spring.dto.*;
import com.twy.tripwithyou_spring.service.ReplyService;
import com.twy.tripwithyou_spring.service.UploadPreferService;
import com.twy.tripwithyou_spring.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private UploadService uploadService;
    private ReplyService replyService;
    private UploadPreferService uploadPreferService;
    @Value("${img.upload.path}")
    private String imgPath;

    public UploadController(UploadService uploadService, ReplyService replyService, UploadPreferService uploadPreferService) {
        this.uploadService = uploadService;
        this.replyService = replyService;
        this.uploadPreferService = uploadPreferService;
    }

    @GetMapping("uploadMain")
    public String main(PagingDto paging,
                       Model model,
                       HttpServletRequest req,
                       // 필터를 위한 파라미터 추가
                       @RequestParam(required = false, name = "orderField", defaultValue = "upload_no") String orderField) {
        //22.12.25 khs add - imgPath에 upload 폴더명 추가하기. 다른 방법도 있을 것 같고, 그냥 img 안에 다 저장하는 것도 방법인 것 같습니다!
        if (!imgPath.contains("upload")) {
            imgPath += "/upload";
        }
        //uptype=1 인 전체 페이징 리스트
        paging.setQueryString(req.getParameterMap());
        paging.setOrderField("upload_no");
        if (orderField!=null){
            paging.setOrderField(orderField); // 12.27 들어오는 order에 따라 세팅되도록 변경함.
        }
        List<UploadDto> uploadDtoList = uploadService.list(paging, 1);
        //인기글 상위 10개 불러오기 12.25 khs add - 인기글도 paging을 넣어서 하나의 페이지로 재사용할 수 있도록 수정해봤습니다.
        PagingDto popularListMain = new PagingDto(1, 10, "prefer", "DESC");
        List<UploadDto> uploadPopularList = uploadService.popularList(popularListMain);
        model.addAttribute("uploadList", uploadDtoList);
        model.addAttribute("paging", paging);
        model.addAttribute("popularList", uploadPopularList);
        return "upload/uploadMain";
    }

    @GetMapping("/detail.do")
    public String detail(Model model,
                         @RequestParam int uploadNo,
                         @SessionAttribute(required = false) UserDto loginInfo) {
        UploadDto detail = uploadService.detail(uploadNo);
        List<ReplyDto> replyList = replyService.uploadDetailList(uploadNo);
        int replyCount = replyList.size();
        if (replyList != null) {
            detail.setReplyList(replyList);
        }
        if(loginInfo!=null){
            UploadPreferDto loginUserPrefer=uploadPreferService.detail(uploadNo,loginInfo.getUserId());
            detail.getUploadPreferView().setLoginUserPrefer(loginUserPrefer);
        }
        model.addAttribute("detail", detail);
        model.addAttribute("replyList", replyList);
        model.addAttribute("replyCount", replyCount);
        return "upload/detail";
    }

    // 12.25 parameter 에 loginInfo 추가
    @GetMapping("/modify.do")
    public String update(Model model,
                         @SessionAttribute UserDto loginInfo,
                         @RequestParam int uploadNo) {
        UploadDto upload = uploadService.detail(uploadNo);
        model.addAttribute("upload", upload);
        return "upload/modify";
    }

    // 22.12.25 khs add - modify post 방식 추가 진행 중(미완) view가 나오지 않아 view 대략적으로 만든 후 테스트 해보며 수정해나갈 예정입니다!
    @PostMapping("/modify.do")
    public String modify(UploadDto upload,
                         @SessionAttribute UserDto loginInfo,
                         @RequestParam(required = false, name = "imgFile") MultipartFile[] imgFiles,
                         @RequestParam(name = "delImgNo", required = false) int[] delImgNos) {
        int modify = 0;
        List<String> saveFileNameList = new ArrayList<>();
        List<UploadImgDto> delImgList = null;
        if (loginInfo.getUserId().equals(upload.getUserId())) {
            try {
                List<UploadImgDto> uploadImgList = new ArrayList<>();
                for (MultipartFile imgFile : imgFiles) {
                    String saveFileName = ImgFileUploadUtil.saveImg(imgFile, imgPath, "upload");
                    if (saveFileName != null) {
                        saveFileNameList.add(saveFileName);
                        UploadImgDto uploadImg = new UploadImgDto();
                        uploadImg.setImgPath(saveFileName);
                        uploadImgList.add(uploadImg);
                    }
                }
                upload.setUploadImgList(uploadImgList);
                delImgList = uploadService.modify(upload, delImgNos);
                modify = 1;
            } catch (Exception e) {
                int delCount = ImgFileUploadUtil.remove(imgPath, saveFileNameList);
                System.out.println("오류로 저장이 취소되었습니다. 삭제된 이미지 개수 : " + delCount);
                e.printStackTrace();
            }
        }
        if (modify > 0) {
            try {
                if (delImgList != null) {
                    for (UploadImgDto delImg : delImgList) {
                        ImgFileUploadUtil.remove(imgPath, delImg.getImgPath());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "redirect:/upload/detail.do?uploadNo=" + upload.getUploadNo();
        } else {
            return "redirect:/upload/modify.do?uploadNo=" + upload.getUploadNo();
        }
    }
    //12.27 필터를 위한 추가
    @GetMapping("/register.do")
    public String  register(@SessionAttribute UserDto loginInfo,
                         Model model) {
        return "/upload/register";
    }

    @PostMapping("/register.do")
    public String register(@RequestParam(required = false, name = "imgFile") MultipartFile[] imgFiles,
                           @SessionAttribute UserDto loginInfo,
                           UploadDto upload) {
        int register = 0;
        if (loginInfo.getUserId().equals(upload.getUserId())) {
            try {
                List<UploadImgDto> uploadImgList = new ArrayList<>();
                for (MultipartFile imgFile : imgFiles) {
                    if (imgFile != null && !imgFile.isEmpty()) {
                        String[] contentType = imgFile.getContentType().split("/");
                        if (contentType[0].equals("image")) {
                            try {
                                String fileName = "upload_" + System.currentTimeMillis() + "_" + (int) (Math.random() * 10000) + "." + contentType[1];
                                Path path = Paths.get(imgPath + "/" + fileName);
                                imgFile.transferTo(path);
                                UploadImgDto uploadImg = new UploadImgDto();
                                uploadImg.setImgPath(fileName);
                                uploadImgList.add(uploadImg);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                upload.setUploadImgList(uploadImgList);
                register = uploadService.register(upload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (register > 0) {
            return "redirect:/upload/detail.do?uploadNo=" + upload.getUploadNo();
        } else {
            return "redirect:/upload/register.do";
        }
    }
    //12.27 검색 페이지 구현
    @GetMapping("/search.do")
    public String search(@RequestParam(name = "search") String search,
                         @RequestParam(name = "condition") String condition,
                         Model model,
                         PagingDto paging,
                         HttpServletRequest req){
        String uri = req.getRequestURI();
        paging.setQueryString(req.getParameterMap());
        String parameter=paging.getQueryString();
        System.out.println("paging parameter:" + paging.getQueryString());
        paging.setOrderField(condition);
        List<UploadDto> searchList=uploadService.searchingList(search,condition,paging,1);
        if(search.trim().equals("") || searchList.size()<1){
            searchList=null;
        }
        model.addAttribute("searchList",searchList);
        model.addAttribute("paging",paging);
        model.addAttribute("search",search);
        model.addAttribute("uri",uri);
        model.addAttribute("parameter",parameter);
        return "/upload/search";
    }

    @GetMapping("/remove.do")
    public String remove(int uploadNo,
                         @SessionAttribute UserDto loginInfo){
        int remove=0;
        UploadDto upload=uploadService.detail(uploadNo);
        if(loginInfo.getUserId().equals(upload.getUserId())){
            try{
                if (upload.getUploadImgList()!=null&&!upload.getUploadImgList().isEmpty()){
                    for(UploadImgDto imgFile : upload.getUploadImgList()){
                        ImgFileUploadUtil.remove(imgPath,imgFile.getImgPath());
                    }
                }
                remove=uploadService.delete(upload);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(remove>0){
            return "redirect:/upload/uploadMain";
        }else{
            return "redirect:/upload/detail.do?uploadNo="+uploadNo;
        }
    }
    @GetMapping("/popular.do")
    public String popular(Model model,
                          PagingDto paging,
                          @RequestParam(required = false) String orderField
    ){
        if(orderField!=null){
            paging.setOrderField("prefer");
        }
        paging.setOrderField("prefer");
        List<UploadDto> uploadPopularList = uploadService.popularList(paging);
        model.addAttribute("uploadList",uploadPopularList);
        model.addAttribute("paging",paging);
        return "/upload/popular";
    }
}
