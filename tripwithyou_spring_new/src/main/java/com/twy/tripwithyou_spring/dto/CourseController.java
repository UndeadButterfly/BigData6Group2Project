package com.twy.tripwithyou_spring.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twy.tripwithyou_spring.service.CoursePlaceService;
import com.twy.tripwithyou_spring.service.CourseService;
import com.twy.tripwithyou_spring.service.VehicleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping(value = "/register",consumes = "application/json",produces = "application/json")
    //    public String register(CourseDto course,
//                         List<CoursePlaceDto> coursePlaceList,
//                         List<VehicleDto> vehicleList) {
    public String register(@RequestBody CourseDto course
                           ) {
        System.out.println(course);
        return null;
    }

    @GetMapping("/map")
    public void map() {}
    @PostMapping("/map")
    public String map(HttpSession session,
                      @RequestParam(name = "json") String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(json);
        try {
            List<CoursePlaceDto> coursePlaceList = objectMapper.readValue(json, new TypeReference<List<CoursePlaceDto>>(){});
            session.setAttribute("coursePlaceList", coursePlaceList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/course/register";
    }
}
