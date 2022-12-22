package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.service.MatchingService;
import com.twy.tripwithyou_spring.service.MatchingServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/matchingRegister.do")
    public String register(MatchingDto matching) {
        int register=0;
        try {
            register = matchingService.register(matching);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (register > 0) {
            return "redirect:/matching/matchingMain";
        } else {
            return "redirect:/matching/matchingMain";
        }
    }


    @GetMapping("/matchingDetail.do")
    public String detail() {
        return "/matching/matchingDetail";
    }

}