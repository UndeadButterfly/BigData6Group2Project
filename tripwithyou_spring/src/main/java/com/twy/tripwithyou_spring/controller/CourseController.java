package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/courseMain")
    public String main(Model model){
        List<List<CourseDto>> courseListsList = courseService.mainList();
        for(List<CourseDto> list : courseListsList) {
            System.out.println(list);
        }
        model.addAttribute("courseListsList", courseListsList);
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
