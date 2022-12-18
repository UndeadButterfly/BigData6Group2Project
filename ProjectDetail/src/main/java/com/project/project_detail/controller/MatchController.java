package com.project.project_detail.controller;

import com.project.project_detail.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class MatchController {
    private MatchService matchService;
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
}
