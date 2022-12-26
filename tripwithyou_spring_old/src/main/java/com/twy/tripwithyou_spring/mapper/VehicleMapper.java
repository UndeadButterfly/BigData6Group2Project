package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import com.twy.tripwithyou_spring.dto.VehicleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    List<VehicleDto> findAll();
    List<VehicleDto> findByCourseNo(int courseNo);
    int deleteByCourseNo(int courseNo);
    int insert(VehicleDto vehicle);
}
