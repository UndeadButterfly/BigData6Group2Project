package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CourseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper extends CRUD<CourseDto,String> {
}
