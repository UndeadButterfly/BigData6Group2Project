package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoursePlaceMapper{
    List<CoursePlaceDto> findAll();
    List<CoursePlaceDto> findByCourseNo(int courseNo);
    int deleteByCourseNo(int courseNo);
    int insert(CoursePlaceDto coursePlace);
}