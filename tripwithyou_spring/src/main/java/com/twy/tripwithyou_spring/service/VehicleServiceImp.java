package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.VehicleDto;
import com.twy.tripwithyou_spring.mapper.VehicleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {
    private VehicleMapper vehicleMapper;

    public VehicleServiceImp(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public List<VehicleDto> list(int courseNo) {
        return vehicleMapper.findByCourseNo(courseNo);
    }

    @Override
    public int register(VehicleDto vehicle) {
        return vehicleMapper.insert(vehicle);
    }

    @Override
    public int modify(int courseNo, List<VehicleDto> vehicleList) {
        int delete = vehicleMapper.deleteByCourseNo(courseNo);
        if (delete <= 0) {
            return delete;
        }
        int modify = 0;
        for (VehicleDto vehicle : vehicleList) {
            modify += vehicleMapper.insert(vehicle);
        }
        return modify;
    }
}
