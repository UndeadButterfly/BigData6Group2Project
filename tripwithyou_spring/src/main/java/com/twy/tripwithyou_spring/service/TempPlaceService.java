package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.dto.TempPlaceDto;

import java.util.List;

public interface TempPlaceService {
    List<TempPlaceDto> list(int tPlaceNo);
}
