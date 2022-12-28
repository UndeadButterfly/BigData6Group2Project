package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    List<VehicleDto> list(int courseNo);
    int register(VehicleDto vehicle);

    int modify(int courseNo, List<VehicleDto> vehicleList);
}
