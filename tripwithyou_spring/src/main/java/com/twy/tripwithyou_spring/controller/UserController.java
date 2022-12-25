package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login.do")
    public void login() {
    }

    @PostMapping("/login.do")
    public String login(@RequestParam(defaultValue = "") String id,
                        @RequestParam(defaultValue = "") String pw,
                        HttpSession session) {
        UserDto loginInfo = userService.login(id, pw);
        if (loginInfo != null) {
            loginInfo.setUserId(id);
            session.setAttribute("loginInfo", loginInfo);
            Object reqUrl = session.getAttribute("reqUrl");
            if (reqUrl == null) {
                return "redirect:/";
            } else {
                session.removeAttribute("reqUrl");
                return "redirect:" + reqUrl;
            }
        } else {
            return "redirect:/user/login.do";
        }
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session, @SessionAttribute UserDto loginInfo) {
        userService.logout(loginInfo);
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    @GetMapping("/register.do")
    public void register() {
    }

    @PostMapping("/register.do")
    public String register(UserDto user) {
        int register = 0;
        try {
            register = userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (register > 0) {
            return "redirect:/user/login.do";
        } else {
            return "redirect:/user/register.do";
        }
    }
    @GetMapping("/detail.do")
    public void detail(@SessionAttribute UserDto loginInfo){}
}
