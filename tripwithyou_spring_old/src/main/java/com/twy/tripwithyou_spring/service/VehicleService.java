package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    List<VehicleDto> list(int courseNo);
}
