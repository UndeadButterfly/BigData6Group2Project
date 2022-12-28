package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseServiceImpTest {
    @Autowired
    private CourseService courseService;
    @Test
    void mainList() {
    }

    @Test
    void listById() {
        PagingDto pagingDto = new PagingDto(1,10,"course_no","desc");
        List<CourseDto> courseList = courseService.listById(pagingDto,"user1");
        System.out.println(courseList);
    }
}