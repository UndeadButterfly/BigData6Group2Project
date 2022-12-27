package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.ReplyDto;
import com.twy.tripwithyou_spring.service.ReplyService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/reply")
public class ReplyController {
    private ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{uploadNo}/list.do")
    public String list(@PathVariable int uploadNo,
                       PagingDto paging,
                       HttpServletRequest req,
                       Model model){
        paging.setQueryString(req.getParameterMap());
        List<ReplyDto> replyList = replyService.uploadDetailList(uploadNo);
        model.addAttribute("replyList",replyList);
        return "reply/list";
    }

}
