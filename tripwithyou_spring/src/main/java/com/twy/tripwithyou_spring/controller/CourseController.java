package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courseMain")
    public String main(){
        return "/course/courseMain";
    }
}
