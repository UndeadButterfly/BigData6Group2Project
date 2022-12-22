package com.twy.tripwithyou_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @GetMapping("/list")
    public void list(){}

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
