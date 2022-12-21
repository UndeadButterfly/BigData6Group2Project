package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/course_search")
    public void search() {}

    @GetMapping("/course_detail")
    public void detail() {}

    @GetMapping("/1pg")
    public void template(){}

    @GetMapping("/detailb")
    public void detailForm() {}

    @GetMapping("/locb")
    public void placeCollector() {}

    @PostMapping("/locb")
    public String placeCollectorPost() {
        return "redirect:/course/course-detail";
    }
}
