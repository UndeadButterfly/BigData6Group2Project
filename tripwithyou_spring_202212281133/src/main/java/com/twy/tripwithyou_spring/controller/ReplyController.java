package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.ReplyService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                       Model model){
        List<ReplyDto> replyList = replyService.uploadDetailList(uploadNo);
        model.addAttribute("replyList",replyList);
        return "reply/list";
    }
    @PostMapping("/register.do")
    public @ResponseBody AjaxStateHandler register(ReplyDto reply,
                                                   @SessionAttribute UserDto loginInfo) {
        AjaxStateHandler ajaxStateHandler = new AjaxStateHandler();
        int register = 0;
        register = replyService.registerOne(reply);
        ajaxStateHandler.setState(register);
        return ajaxStateHandler;
    }

    @DeleteMapping("/delete.do")
    public @ResponseBody AjaxStateHandler delete(int replyNo, @SessionAttribute UserDto loginInfo) { //로그인 유저만 접근 가능
        AjaxStateHandler ajaxStateHandler = new AjaxStateHandler();
        ReplyDto reply=replyService.detail(replyNo);
        int delete = 0;
        delete = replyService.removeOne(replyNo);
        ajaxStateHandler.setState(delete);
        return ajaxStateHandler;
    }

    @GetMapping("/modify.do")
    public String modify(int replyNo, @SessionAttribute UserDto loginInfo, Model model) {
        ReplyDto reply = replyService.detail(replyNo);
        model.addAttribute("reply", reply);
        return "/reply/modify";
    }

    @PutMapping("/modify.do")
    public @ResponseBody AjaxStateHandler modify(@SessionAttribute UserDto loginInfo,
                                                 ReplyDto reply) {
        System.out.println(reply);
        AjaxStateHandler ajaxStateHandler = new AjaxStateHandler();
        int modify = 0;
        modify = replyService.modifyOne(reply);
        ajaxStateHandler.setState(modify);
        return ajaxStateHandler;
    }

    @Data
    class AjaxStateHandler {
        private int state = 0; //0:실패 1:성공 (statusCode : 400(badRequest), 500(db 통신 오류))
    }

}
