package com.twy.tripwithyou_spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twy.tripwithyou_spring.dto.*;
import com.twy.tripwithyou_spring.service.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;
    private CoursePlaceService coursePlaceService;
    private VehicleService vehicleService;
    private UploadService uploadService;
    private UploadHashService uploadHashService;
    private Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Value("${img.upload.path}")
    private String imgPath;
    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseController(CourseService courseService,
                            CoursePlaceService coursePlaceService,
                            VehicleService vehicleService,
                            UploadService uploadService,
                            UploadHashService uploadHashService) {
        this.courseService = courseService;
        this.coursePlaceService = coursePlaceService;
        this.vehicleService = vehicleService;
        this.uploadService = uploadService;
        this.uploadHashService = uploadHashService;
    }

    @GetMapping("/courseMain")
    public String main(Model model) {
        List<List<CourseDto>> courseListsList = courseService.mainList();
        model.addAttribute("courseListsList", courseListsList);
        return "/course/courseMain";
    }

    @GetMapping("/searchBar")
    public void search() {
    }

    @PostMapping("/searchBar")
    public void search(List<String> searchWords) {
    }

    //    ------------------------TEMPORARY----------------------
    @PostMapping("/courseMain")
    public String tempSearch(
            @RequestParam String searchRegion,
            @RequestParam String searchText,
            HttpSession session
    ) {
        session.setAttribute("searchRegion", searchRegion);
        session.setAttribute("searchText", searchText);
        return "redirect:/course/searchResult";
    }

    @PostMapping("/searchResult")
    public String temp2Search(
            @RequestParam String searchRegion,
            @RequestParam String searchText,
            HttpSession session
    ) {
        session.setAttribute("searchRegion", searchRegion);
        session.setAttribute("searchText", searchText);
        return "redirect:/course/searchResult";
    }
//    ------------------------TEMPORARY----------------------

    @GetMapping("/searchResult") //검색 결과
    public String searchResult(HttpSession session,
                             Model model) {
        List<UploadHashDto> uploadHashList = uploadHashService.search("%" + session.getAttribute("searchRegion").toString() + "%", 2);
        String[] searchTextSplit = session.getAttribute("searchText").toString().split(" ");
        if (session.getAttribute("searchText").toString() != "") {
            for (String searchText : searchTextSplit) {
                String text = "%" + searchText + "%";
                uploadHashList.addAll(uploadHashService.search(text, 2));
            }
        }
        HashSet<CourseDto> courseSet = new HashSet<>();
        for (UploadHashDto uploadHash : uploadHashList) {
            CourseDto course = courseService.showByUploadNo(uploadHash.getUploadNo());
            courseSet.add(course);
        }
        model.addAttribute("courseSet", courseSet);
        return "/course/searchResult";
    }

    @GetMapping("/{courseNo}/detail")
    public String detail(Model model,
                         @PathVariable int courseNo) {
        CourseDto course = courseService.detail(courseNo);
        List<CoursePlaceDto> coursePlaceList = coursePlaceService.list(courseNo);
        List<VehicleDto> vehicleList = vehicleService.list(courseNo);
        List<Integer> cardsForDay = new ArrayList<>();
        for (int i = 0; i < course.getDuration(); i++) {
            final int day = i;
            int num = 0;
            num += coursePlaceList.stream().filter(p -> p.getPDay() == (day + 1)).count();
            num += vehicleList.stream().filter(v -> v.getVDay() == (day + 1)).count();
            cardsForDay.add(num);
        }
        model.addAttribute("course", course);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("vehicleList", vehicleList);
        model.addAttribute("cardsForDay", cardsForDay);
        return "/course/detail";
    }

    @GetMapping("/{courseNo}/modifyMap")
    public String modifyMap(Model model,
                            @PathVariable int courseNo) {
        System.out.println(courseNo);
        List<CoursePlaceDto> coursePlaceList = coursePlaceService.list(courseNo);
        System.out.println(coursePlaceList);
        List<String> coursePlaceJsonList = new ArrayList<>();
        try {
            for (CoursePlaceDto coursePlace : coursePlaceList) {
                coursePlaceJsonList.add(objectMapper.writeValueAsString(coursePlace));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(coursePlaceJsonList);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("coursePlaceJsonList", coursePlaceJsonList);
        model.addAttribute("courseNo", courseNo);
        return "/course/modifyMap";
    }
    @PostMapping("/{courseNo}/modifyMap")
    public String modifyMap(HttpSession session,
                            @RequestParam(name = "json") String json,
                            @PathVariable int courseNo,
                            @SessionAttribute UserDto loginInfo) {
        try {
            List<CoursePlaceDto> coursePlaceList = objectMapper.readValue(json, new TypeReference<List<CoursePlaceDto>>() {
            });
            List<String> jsonList = new ArrayList<>();
            for (CoursePlaceDto coursePlace : coursePlaceList) {
                jsonList.add(objectMapper.writeValueAsString(coursePlace));
            }
            session.setAttribute("coursePlaceList", coursePlaceList);
            session.setAttribute("jsonList", jsonList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/course/" + courseNo + "/modify";
    }

    @GetMapping("/{courseNo}/modify")
    public String modify(Model model,
                         @PathVariable int courseNo,
                         @SessionAttribute UserDto loginInfo) {
        CourseDto course = courseService.detail(courseNo);
        List<CoursePlaceDto> coursePlaceList = coursePlaceService.list(courseNo);
        List<VehicleDto> vehicleList = vehicleService.list(courseNo);
        List<Integer> cardsForDay = new ArrayList<>();
        for (int i = 0; i < course.getDuration(); i++) {
            final int day = i;
            int num = 0;
            num += coursePlaceList.stream().filter(p -> p.getPDay() == (day + 1)).count();
            num += vehicleList.stream().filter(v -> v.getVDay() == (day + 1)).count();
            cardsForDay.add(num);
        }
        List<String> coursePlaceJsonList = new ArrayList<>();
        List<String> vehicleJsonList = new ArrayList<>();
        try {
            for (CoursePlaceDto coursePlace : coursePlaceList) {
                coursePlaceJsonList.add(objectMapper.writeValueAsString(coursePlace));
            }
            for (VehicleDto vehicle : vehicleList) {
                vehicleJsonList.add(objectMapper.writeValueAsString(vehicle));
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("course", course);
        model.addAttribute("coursePlaceList", coursePlaceList);
        model.addAttribute("vehicleList", vehicleList);
        model.addAttribute("cardsForDay", cardsForDay);
        model.addAttribute("coursePlaceJsonList", coursePlaceJsonList);
        model.addAttribute("vehicleJsonList", vehicleJsonList);

        return "/course/modify";
    }

    @PostMapping("/{courseNo}/modify")
    public String modify(@RequestParam(name = "courseJson") String courseJson,
                         @RequestParam(name = "uploadJson") String uploadJson,
                         @RequestParam(name = "placeListJson") String placeListJson,
                         @RequestParam(name = "vehicleListJson") String vehicleListJson,
                         @PathVariable(name = "courseNo") int courseNo,
                         @RequestParam(name = "imgFile", required = false) MultipartFile imgFile,
                         @RequestParam(name = "imgFileOld", required = false) String imgFileOld,
                         @SessionAttribute UserDto loginInfo
    ) {
        System.out.println(imgFile);
        try {

//            courseJson parse
            CourseDto course = objectMapper.readValue(courseJson, new TypeReference<CourseDto>() {
            });
//            uploadJson parse
            UploadDto upload = objectMapper.readValue(uploadJson, new TypeReference<UploadDto>() {
            });
//            coursePlaceJsonList parse
            List<CoursePlaceDto> coursePlaceList = new ArrayList<>();
            List<CoursePlaceDto> coursePlaceJsonList = objectMapper.readValue(placeListJson, new TypeReference<List<CoursePlaceDto>>() {
            });
            for (CoursePlaceDto coursePlace : coursePlaceJsonList) {
                coursePlace.setCourseNo(courseNo);
                coursePlaceList.add(coursePlace);
            }
//            vehicleJsonList parse
            List<VehicleDto> vehicleList = new ArrayList<>();
            List<VehicleDto> vehicleJsonList = objectMapper.readValue(vehicleListJson, new TypeReference<List<VehicleDto>>() {
            });
            for (VehicleDto vehicle : vehicleJsonList) {
                vehicle.setCourseNo(courseNo);
                vehicleList.add(vehicle);
            }
            course.setCoursePlaceList(coursePlaceList);
            course.setVehicleList(vehicleList);
            course.setUploadDto(upload);
            // course parsing finished
            if(loginInfo.getUserId().equals(course.getUserId())) {
                if (imgFile != null && !imgFile.isEmpty()) {
                    String[] contentsTypes = imgFile.getContentType().split("/");
                    if (contentsTypes[0].equals("image")) {
                        try {
                            String fileName = "course_" + System.currentTimeMillis() + "_" + (int) (Math.random() * 10000 + 1) + "." + contentsTypes[1];
                            Path path = Paths.get(imgPath + "/" + fileName);
                            imgFile.transferTo(path);
                            course.setImage(fileName);
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                } else {
                    course.setImage(imgFileOld);
                }
                int modify = courseService.modify(course);
                if (modify < 2) {
                    return "redirect:/course/" + courseNo + "/modify";
                }
                modify = coursePlaceService.modify(courseNo, coursePlaceList);
                if (modify < coursePlaceList.size()) {
                    return "redirect:/course/" + courseNo + "/modify";
                }
                modify = vehicleService.modify(courseNo, vehicleList);
                if (modify < vehicleList.size()) {
                    return "redirect:/course/" + courseNo + "/modify";
                }
                // modification was successful
                int modifyHash = uploadHashService.modifyCourseHash(course);
                if (!(modifyHash > 0)) System.out.println("검색어 등록 안됨!");
                return "redirect:/course/" + courseNo + "/detail";
            } else {
                return "redirect:/course/courseMain";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/register")
    public String register(Model model,@SessionAttribute UserDto loginInfo) {
        return "/course/register";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "courseNo") int courseNo) {
        System.out.println("delete페이지입니다.");
        CourseDto course = courseService.detail(courseNo);
        int uploadNo = course.getUploadNo();
        int delete = 0;
        delete = uploadService.delete(uploadNo);
        if (uploadNo > 0) {
            return "redirect:/course/courseMain";
        } else {
            return "redirect:/course/" + courseNo + "/detail";
        }
    }
    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "courseJson") String courseJson,
                           @RequestParam(name = "uploadJson") String uploadJson,
                           @RequestParam(name = "placeListJson") String placeListJson,
                           @RequestParam(name = "vehicleListJson") String vehicleListJson,
                           @RequestParam(name = "imgFile", required = false) MultipartFile imgFile,
                           @SessionAttribute UserDto loginInfo
    ) {
        int register = 0;
        CourseDto course = null;
        UploadDto upload = null;
        List<CoursePlaceDto> coursePlaceList = null;
        List<VehicleDto> vehicleList = null;
        try {
//            courseJson parse
            course = objectMapper.readValue(courseJson, new TypeReference<CourseDto>() {
            });
//            System.out.println(course);
//            uploadJson parse
            upload = objectMapper.readValue(uploadJson, new TypeReference<UploadDto>() {
            });
//            System.out.println(upload);
//            coursePlaceJsonList parse
            coursePlaceList = new ArrayList<>();
            List<CoursePlaceDto> coursePlaceJsonList = objectMapper.readValue(placeListJson, new TypeReference<List<CoursePlaceDto>>() {
            });
            for (CoursePlaceDto coursePlace : coursePlaceJsonList) {
//                System.out.println(coursePlace);
                coursePlaceList.add(coursePlace);
            }
//            vehicleJsonList parse
            vehicleList = new ArrayList<>();
            List<VehicleDto> vehicleJsonList = objectMapper.readValue(vehicleListJson, new TypeReference<List<VehicleDto>>() {
            });
            for (VehicleDto vehicle : vehicleJsonList) {
//                System.out.println(vehicle);
                vehicleList.add(vehicle);
            }
            course.setCoursePlaceList(coursePlaceList);
            course.setVehicleList(vehicleList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (loginInfo.getUserId().equals(upload.getUserId())) {
            course.setCoursePlaceList(coursePlaceList);
            course.setVehicleList(vehicleList);
            if (imgFile != null && !imgFile.isEmpty()) {
                String[] contentsTypes = imgFile.getContentType().split("/");
                if (contentsTypes[0].equals("image")) {
                    try {
                        String fileName = "course_" + System.currentTimeMillis() + "_" + (int) (Math.random() * 10000 + 1) + "." + contentsTypes[1];
                        Path path = Paths.get(imgPath + "/" + fileName);
                        imgFile.transferTo(path);
                        course.setImage(fileName);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
            course.setUploadDto(upload);
            register = courseService.register(course);

        } else {
            return "redirect:/course/courseMain";
        }
        int courseNo = course.getCourseNo();
        if (register > 1) {
            int listRegister = 0;
            for (CoursePlaceDto coursePlace : coursePlaceList) {
                coursePlace.setCourseNo(courseNo);
                listRegister += coursePlaceService.register(coursePlace);
            }
            for (VehicleDto vehicle : vehicleList) {
                vehicle.setCourseNo(courseNo);
                listRegister += vehicleService.register(vehicle);
            }
            if (listRegister > 0) {
                int registerHash = uploadHashService.registerCourseHash(course);
                if (!(registerHash > 0)) System.out.println("검색어 등록 안됨!");
                return "redirect:/course/" + courseNo + "/detail";
            } else {
                return "redirect:/course/map";
            }
        } else {
            return "redirect:/course/register";
        }

    }

    @GetMapping("/map")
    public void map() {
    }

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