package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.TemplateDto;
import com.twy.tripwithyou_spring.service.TempImgService;
import com.twy.tripwithyou_spring.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @Autowired
    private TemplateService templateService;
    @Autowired
    private TempImgService tempImgService;

    public TemplateController(TemplateService templateService, TempImgService tempImgService) {
        this.templateService = templateService;
        this.tempImgService = tempImgService;
    }
    @GetMapping("/list")
    public String list(Model model){
        List<TemplateDto> restTemplateList = templateService.listType("식도락");
        List<TemplateDto> actTemplateList = templateService.listType("액티비티");
        System.out.println(restTemplateList);
        List<List<TemplateDto>> templateListsList = new ArrayList<>();
        templateListsList.add(restTemplateList);
        templateListsList.add(actTemplateList);
        model.addAttribute("templateListsList", templateListsList);

        return "/template/list1";

    }

    @GetMapping("/modify")
    public void modify(){}
    @PostMapping ("/modify")
    public void modifyPost(){}

    @GetMapping("/register")
    public void register(){}
    @PostMapping("/register")
    public void registerPost(){}

    @GetMapping("/remove")
    public void remove(){}
}