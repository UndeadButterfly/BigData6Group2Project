package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.PagingDto;

import java.util.List;

public interface CourseService {
    List<List<CourseDto>> mainList();
    CourseDto detail(int courseNo);

    List<CourseDto> list(PagingDto pagingDto);

    List<CourseDto> listById(PagingDto pagingDto, String userId);
    int register(CourseDto course);
    int modify(CourseDto course);
    CourseDto showByUploadNo(int uploadNo);
}
