package com.twy.tripwithyou_spring.controller;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.dto.VehicleDto;
import com.twy.tripwithyou_spring.service.CoursePlaceService;
import com.twy.tripwithyou_spring.service.CourseService;
import com.twy.tripwithyou_spring.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    private CoursePlaceService coursePlaceService;
    private VehicleService vehicleService;

    public CourseController(CourseService courseService,
                            CoursePlaceService coursePlaceService,
                            VehicleService vehicleService) {
        this.courseService = courseService;
        this.coursePlaceService = coursePlaceService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/courseMain")
    public String main(Model model) {
        List<List<CourseDto>> courseListsList = courseService.mainList();
        for (List<CourseDto> list : courseListsList) {
            System.out.println(list);
        }
        model.addAttribute("courseListsList", courseListsList);
        return "/course/courseMain";
    }

    @GetMapping("/searchBar")
    public void search() {}

    @PostMapping("/searchBar")
    public void search(List<String> searchWords) {}

    //    ------------------------TEMPORARY----------------------
    @PostMapping("/courseMain")
    public String tempSearch(){
        return "redirect:/course/searchResult";
    }
    @PostMapping("/searchResult")
    public String temp2Search(){
        return "redirect:/course/searchResult";
    }
//    ------------------------TEMPORARY----------------------

    @GetMapping("/searchResult") //검색 결과
    public void searchResult() {
    }

    @GetMapping("/{courseNo}/detail")
    public String detail(Model model,
                         @PathVariable int courseNo) {
        CourseDto course = courseService.detail(courseNo);
        List<CoursePlaceDto> coursePlaceList = coursePlaceService.list(courseNo);
        List<VehicleDto> vehicleList = vehicleService.list(courseNo);
        model.addAttribute("course", course);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("vehicleList", vehicleList);
        System.out.println("detail 페이지 입니다");
        System.out.println(course);
        System.out.println(coursePlaceList);
        System.out.println(vehicleList);
        return "/course/detail";
    }

    @GetMapping("/{courseNo}/modify")
    public String modify(Model model,
                         @PathVariable int courseNo) {
        CourseDto course = courseService.detail(courseNo);
        List<CoursePlaceDto> coursePlaceList = coursePlaceService.list(courseNo);
        List<VehicleDto> vehicleList = vehicleService.list(courseNo);
        model.addAttribute("course", course);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("vehicleList", vehicleList);
        System.out.println("modify 페이지 입니다");
        System.out.println(course);
        System.out.println(coursePlaceList);
        System.out.println(vehicleList);
        return "/course/modify";
    }

    @PostMapping("/{courseNo}/modify")
    public void modify(){}

    @GetMapping("/register")
    public void register(Model model) {}

    @PostMapping("/register")
//    public String register(CourseDto course,
//                         List<CoursePlaceDto> coursePlaceList,
//                         List<VehicleDto> vehicleList) {
    public String register() {
        return "redirect:/course/1/detail";
    }

    @GetMapping("/map")
    public void map() {}
    @PostMapping("/map")
    public String map(List<String> placeIdList) {
        return "redirect:/course/register";
    }
}