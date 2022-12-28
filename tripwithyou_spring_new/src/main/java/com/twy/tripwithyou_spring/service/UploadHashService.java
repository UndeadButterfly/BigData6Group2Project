package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.UploadHashDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface UploadHashService {
    int registerCourseHash(CourseDto course);
    List<UploadHashDto> search(String word, int uptype);

    int modifyCourseHash(CourseDto course);
}
