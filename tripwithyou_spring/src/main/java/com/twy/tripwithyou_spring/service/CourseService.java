package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;

import java.util.List;

public interface CourseService {
    List<List<CourseDto>> mainList();
    CourseDto detail(int courseNo);
}
