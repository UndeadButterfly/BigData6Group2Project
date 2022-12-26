package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.MatchingService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/matching")
public class MatchingController {
    private MatchingService matchingService;
    private Logger log=LoggerFactory.getLogger(this.getClass().getSimpleName());

    public MatchingController(MatchingService matchingService) {
        this.matchingService = matchingService;
    }

    @GetMapping("/matchingMain")
    public String main(Model model,
                       PagingDto paging,
                       HttpServletRequest req) {
        if(paging.getOrderField()==null)paging.setOrderField("matching_no");
        paging.setQueryString(req.getParameterMap());
        List<MatchingDto> matchingList = matchingService.list(paging);
        model.addAttribute("matchingList",matchingList);
        model.addAttribute("paging",paging);
        log.info(paging.toString());
        return "/matching/matchingMain";
    }

    @GetMapping("/matchingRegister.do")
    public String register(@SessionAttribute UserDto loginInfo){
        return "/matching/matchingRegister";
    }

    @GetMapping("/matchingDetail.do")
    public ModelAndView detail(
            @RequestParam(name = "matchingNo") int matchingNo,
            ModelAndView model) throws Exception {
            MatchingDto matching=matchingService.detail(matchingNo);
            model.addObject("matching",matching);
            model.setViewName("/matching/matchingDetail");
        return model;
    }

    @PostMapping("/matchingRegister.do")
    public String register(MatchingDto matching) {
        int register = 0;
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
}