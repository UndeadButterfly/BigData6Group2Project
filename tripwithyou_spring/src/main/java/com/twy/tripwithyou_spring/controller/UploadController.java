package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import com.twy.tripwithyou_spring.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }
    @GetMapping("uploadMain")
    public String main(PagingDto paging,
                       Model model,
                       HttpServletRequest req){
        paging.setQueryString(req.getParameterMap());
        List<UploadDto> uploadDtoList=uploadService.list(paging);
        model.addAttribute("uploadList",uploadDtoList);
        model.addAttribute("paging",paging);
        return "upload/uploadMain";
    }
    @GetMapping("/detail.do")
    public String detail(Model model,
                         @RequestParam int uploadNo){

        System.out.println(uploadNo);
        UploadDto detail=uploadService.detail(uploadNo);
        model.addAttribute("detail",detail);
        return "upload/detail";
    }
    @GetMapping("/update.do")
    public void update(){}
}
