package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.*;
import com.twy.tripwithyou_spring.service.CourseService;
import com.twy.tripwithyou_spring.service.MatchingService;
import com.twy.tripwithyou_spring.service.UploadService;
import com.twy.tripwithyou_spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UploadService uploadService;
    private CourseService courseService;
    private MatchingService matchingService;

    public UserController(UserService userService, UploadService uploadService, CourseService courseService, MatchingService matchingService) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.courseService = courseService;
        this.matchingService = matchingService;
    }

    @GetMapping("/login.do")
    public void login(HttpSession session,
                      HttpServletRequest req) {
        String referer=req.getHeader("referer");
        session.setAttribute("redirectUri",referer);
    }

    @PostMapping("/login.do")
    public String login(@RequestParam(defaultValue = "") String id,
                        @RequestParam(defaultValue = "") String pw,
                        HttpSession session,
                        @SessionAttribute(required = false) String redirectUri) {
        UserDto loginInfo = userService.login(id, pw);
        if (loginInfo != null) {
            loginInfo.setAges();
            loginInfo.setUserId(id);
            session.setAttribute("loginInfo", loginInfo);
            if (redirectUri == null) {
                return "redirect:/";
            } else {
                session.removeAttribute("redirectUri");
                return "redirect:" + redirectUri;
            }
        } else {
            return "redirect:/user/login.do";
        }
    }

    @GetMapping("/logout.do")
    public String logout(HttpSession session, @SessionAttribute UserDto loginInfo,HttpServletRequest req) {
        userService.logout(loginInfo);
        session.removeAttribute("loginInfo");
        String redirectUri = req.getHeader("referer");
        if (redirectUri == null) {
            return "redirect:/";
        } else {
            return "redirect:"+redirectUri;
        }
    }

    @GetMapping("/register.do")
    public void register() {
    }

    @PostMapping("/register.do")
    public String register(UserDto user) {
        user.setAges();
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
    public void detail(@SessionAttribute UserDto loginInfo) {
    }

    @GetMapping("/myUpload.do")
    public String myUpload(HttpServletRequest req, @SessionAttribute UserDto loginInfo, Model model, PagingDto paging) {
        String userId = loginInfo.getUserId();
        if (paging.getOrderField() == null) paging.setOrderField("upload_no");
        paging.setQueryString(req.getParameterMap());
        List<UploadDto> uploadList = uploadService.list(paging, userId);
        model.addAttribute("uploadList", uploadList);
        model.addAttribute("paging", paging);
        return "/user/myUpload";
    }

    @GetMapping("/myCourse.do")
    public String myCourse(HttpServletRequest req, @SessionAttribute UserDto loginInfo, Model model, PagingDto paging) {
        String userId = loginInfo.getUserId();
        if (paging.getOrderField() == null) paging.setOrderField("course_no");
        paging.setQueryString(req.getParameterMap());
        List<CourseDto> courseList = courseService.listById(paging, userId);
        model.addAttribute("courseList", courseList);
        model.addAttribute("paging", paging);
        return "/user/myCourse";
    }

    @GetMapping("/myMatching.do")
    public String myMatching(HttpServletRequest req, @SessionAttribute UserDto loginInfo, Model model, PagingDto paging) {
        String userId = loginInfo.getUserId();
        if (paging.getOrderField() == null) paging.setOrderField("matching_no");
        paging.setQueryString(req.getParameterMap());
        List<MatchingDto> matchingList = matchingService.listById(paging, userId);
        model.addAttribute("matchingList", matchingList);
        model.addAttribute("paging", paging);
        return "/user/myMatching";
    }

    @GetMapping("/delete.do")
    public String delete(@SessionAttribute UserDto loginInfo, HttpSession session) {
        userService.logout(loginInfo);
        userService.withdrawal(loginInfo.getUserId());
        session.removeAttribute("loginInfo");
        return "redirect:/";
    }

    @GetMapping("/modify.do")
    public void modify(@SessionAttribute UserDto loginInfo) {
    }

    @PostMapping("/modify.do")
    public String modify(@SessionAttribute UserDto loginInfo,
                         UserDto user,
                         HttpSession session) {
        user.setAges();
        int modify = 0;
        modify = userService.modify(user);
        if (modify > 0) {
            session.setAttribute("loginInfo", user);
            return "redirect:/user/detail.do";
        } else {
            return "redirect:/user/modify.do";
        }
    }
}
