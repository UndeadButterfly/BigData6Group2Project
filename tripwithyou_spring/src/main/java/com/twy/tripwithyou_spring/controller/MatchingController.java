package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.service.MatchingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matching")
public class MatchingController {
    private MatchingService matchingService;

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }
    @GetMapping("/matchingMain")
    public String main(){
        return "/matching/matchingMain";
    }
    @GetMapping("/matchingRegister.do")
    public String register(){
        return "/matching/matchingRegister";
    }
    @GetMapping("/matchingDetail.do")
    public String detail() { return "/matching/matchingDetail";}
}