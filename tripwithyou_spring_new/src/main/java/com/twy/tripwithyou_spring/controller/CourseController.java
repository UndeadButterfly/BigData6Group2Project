package com.twy.tripwithyou_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twy.tripwithyou_spring.dto.*;
import com.twy.tripwithyou_spring.service.CoursePlaceService;
import com.twy.tripwithyou_spring.service.CoursePlaceServiceImp;
import com.twy.tripwithyou_spring.service.CourseService;
import com.twy.tripwithyou_spring.service.VehicleService;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    private CoursePlaceService coursePlaceService;
    private VehicleService vehicleService;
    private ObjectMapper objectMapper = new ObjectMapper();

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
        List<Integer> cardsForDay = new ArrayList<>();
        for(int i=0; i<course.getDuration(); i++) {
            final int day=i;
            int num=0;
            num+=coursePlaceList.stream().filter(p->p.getPDay()==(day+1)).count();
            num+=vehicleList.stream().filter(v->v.getVDay()==(day+1)).count();
            cardsForDay.add(num);
        }
        model.addAttribute("course", course);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("vehicleList", vehicleList);
        model.addAttribute("cardsForDay", cardsForDay);

        System.out.println("detail 페이지 입니다");
        System.out.println(course);
        System.out.println(coursePlaceList);
        System.out.println(vehicleList);
        return "/course/detail";
    }

    @GetMapping("/{courseNo}/modifyMap")
    public String modifyMap(Model model,
                            @PathVariable int courseNo) {
        model.addAttribute("courseNo", courseNo);
        return "/course/map";
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
    public String register(Model model) {
        return "/course/register";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name="courseJson") String courseJson,
                           @RequestParam(name="uploadJson") String uploadJson,
                           @RequestParam(name="placeListJson") String placeListJson,
                           @RequestParam(name="vehicleListJson") String vehicleListJson
                           ) {
//        System.out.println(courseJson);
//        System.out.println(uploadJson);
//        System.out.println(placeListJson);
//        System.out.println(vehicleListJson);
        CourseDto course = null;
        UploadDto upload = null;
        List<CoursePlaceDto> coursePlaceList = null;
        List<VehicleDto> vehicleList = null;

        try {
//            courseJson parse
            course = objectMapper.readValue(courseJson, new TypeReference<CourseDto>() {});
//            System.out.println(course);
//            uploadJson parse
            upload = objectMapper.readValue(uploadJson, new TypeReference<UploadDto>() {});
//            System.out.println(upload);
//            coursePlaceJsonList parse
           coursePlaceList = new ArrayList<>();
            List<CoursePlaceDto> coursePlaceJsonList = objectMapper.readValue(placeListJson, new TypeReference<List<CoursePlaceDto>>() {});
            for(CoursePlaceDto coursePlace : coursePlaceJsonList) {
//                System.out.println(coursePlace);
                coursePlaceList.add(coursePlace);
            }
//            vehicleJsonList parse
            vehicleList = new ArrayList<>();
            List<VehicleDto> vehicleJsonList = objectMapper.readValue(vehicleListJson, new TypeReference<List<VehicleDto>>() {});
            for(VehicleDto vehicle : vehicleJsonList){
//                System.out.println(vehicle);
                vehicleList.add(vehicle);
            }
            course.setCoursePlaceList(coursePlaceList);
            course.setVehicleList(vehicleList);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("courseList+VehicleList: "+ course);
        course.setUploadDto(upload);
        int register = courseService.register(course);
        int courseNo = course.getCourseNo();
        if(register>1){
            int listRegister = 0;
            for(CoursePlaceDto coursePlace: coursePlaceList){
                coursePlace.setCourseNo(courseNo);
                listRegister += coursePlaceService.register(coursePlace);
            }
            for(VehicleDto vehicle: vehicleList){
                vehicle.setCourseNo(courseNo);
                listRegister += vehicleService.register(vehicle);
            }
            if(listRegister>0){
                return "redirect:/course/"+courseNo+"/detail";
            }else{
                return "redirect:/course/map";
            }
        }else{
            return "redirect:/course/register";
        }

    }

    @GetMapping("/map")
    public void map() {}
    @PostMapping("/map")
    public String map(HttpSession session,
                      @RequestParam(name = "json") String json) {

        try {
            List<CoursePlaceDto> coursePlaceList = objectMapper.readValue(json, new TypeReference<List<CoursePlaceDto>>(){});
            List<String> jsonList = new ArrayList<>();
            for(CoursePlaceDto coursePlace : coursePlaceList) {
                jsonList.add(objectMapper.writeValueAsString(coursePlace));
            }
            session.setAttribute("coursePlaceList", coursePlaceList);
            session.setAttribute("jsonList", jsonList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/course/register";
    }
}
