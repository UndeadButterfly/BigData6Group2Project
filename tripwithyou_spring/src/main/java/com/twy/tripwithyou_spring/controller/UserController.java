package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.UserDto;
import com.twy.tripwithyou_spring.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login.do")
    public String login() {

        return "/user/login";
    }

    @PostMapping("/login.do")
    public String login(@RequestParam(defaultValue = "tttt") String userId,
                        @RequestParam(defaultValue = "tttt") String pw,
                        HttpSession session) {
        UserDto loginUser = null;

        loginUser = userService.login(userId, pw);
        if (loginUser != null) {
            session.setAttribute("loginUser",loginUser);
//            Object reqUrl = session.getAttribute("reqUrl");
//            if (reqUrl == null) {
                return "redirect:/";
//            } else {
//                session.removeAttribute("reqUrl");
//                return "redirect:"+((String) reqUrl);
//            }
        } else {
            return "redirect:/user/login.do";
        }
    }
}
