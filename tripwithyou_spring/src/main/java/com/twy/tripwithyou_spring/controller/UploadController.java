package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.service.UploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/upload")
public class UploadController {
    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }
    @GetMapping("/uploadMain")
    public String main(){
        return "/upload/uploadMain";
    }
}
