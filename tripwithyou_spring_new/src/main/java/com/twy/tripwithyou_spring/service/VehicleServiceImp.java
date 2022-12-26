package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.VehicleDto;
import com.twy.tripwithyou_spring.mapper.VehicleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService{
    private VehicleMapper vehicleMapper;

    public VehicleServiceImp(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDto> list(int courseNo) {
        return vehicleMapper.findByCourseNo(courseNo);
    }
}
