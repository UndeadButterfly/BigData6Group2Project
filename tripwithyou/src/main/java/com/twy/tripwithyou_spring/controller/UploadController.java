package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.ImgFileUploadUtil;
import com.twy.tripwithyou_spring.dto.*;
import com.twy.tripwithyou_spring.service.ReplyService;
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
    @Value("${img.upload.path}")
    private String imgPath;

    public UploadController(UploadService uploadService, ReplyService replyService) {
        this.uploadService = uploadService;
        this.replyService = replyService;
    }

    @GetMapping("uploadMain")
    public String main(PagingDto paging,
                       Model model,
                       HttpServletRequest req) {
        //22.12.25 khs add - imgPath에 upload 폴더명 추가하기. 다른 방법도 있을 것 같고, 그냥 img 안에 다 저장하는 것도 방법인 것 같습니다!
        if (!imgPath.contains("upload")) {
            imgPath += "/upload";
        }
        //uptype=1 인 전체 페이징 리스트
        paging.setQueryString(req.getParameterMap());
        paging.setOrderField("upload_no");
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
                         @RequestParam int uploadNo) {
        System.out.println(uploadNo);
        UploadDto detail = uploadService.detail(uploadNo);
        List<ReplyDto> replyList = replyService.uploadDetailList(uploadNo);
        if (replyList != null) {
            detail.setReplyList(replyList);
        }
        model.addAttribute("detail", detail);
        model.addAttribute("replyList", replyList);
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

    @GetMapping("/register.do")
    public void register() {
    }

    @PostMapping("/register.do")
    public String register(@RequestParam(required = false, name = "imgFile") MultipartFile[] imgFiles,
                           @SessionAttribute UserDto loginInfo,
                           UploadDto upload) {
        int register = 0;
        System.out.println(upload);
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
}
