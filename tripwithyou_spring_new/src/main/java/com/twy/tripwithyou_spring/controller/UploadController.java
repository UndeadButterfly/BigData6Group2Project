package com.twy.tripwithyou_spring.controller;

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
        //uptype=1 인 전체 페이징 리스트
        paging.setQueryString(req.getParameterMap());
        paging.setOrderField("upload_no");
        List<UploadDto> uploadDtoList = uploadService.list(paging, 1);
        //인기글 상위 10개 불러오기
        List<UploadDto> uploadPopularList = uploadService.popularList();
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
        detail.setReplyList(replyList);
        model.addAttribute("detail", detail);
        model.addAttribute("replyList", replyList);
        return "upload/detail";
    }

    @GetMapping("/modify.do")
    public String update(Model model,
                         @RequestParam int uploadNo) {
        UploadDto upload = uploadService.detail(uploadNo);
        model.addAttribute("upload", upload);
        return "upload/modify";
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
        if(loginInfo.getUserId().equals(upload.getUserId())){
        try {
            List<UploadImgDto> uploadImgList = new ArrayList<>();
            for (MultipartFile imgFile : imgFiles) {
                if (imgFile != null && !imgFile.isEmpty()) {
                    String[] contentType = imgFile.getContentType().split("/");
                    if (contentType[0].equals("image")) {
                        try {
                            String fileName = "board_" + System.currentTimeMillis() + "_" + (int) (Math.random() * 10000) + "." + contentType[1];
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
