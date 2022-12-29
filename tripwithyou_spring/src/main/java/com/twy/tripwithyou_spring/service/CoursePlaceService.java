package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;

import java.util.List;

public interface CoursePlaceService {
    List<CoursePlaceDto> list(int courseNo);
    int register(CoursePlaceDto coursePlace);

    int modify(int courseNo, List<CoursePlaceDto> coursePlaceList);
}