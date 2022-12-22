package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends CRUD<CourseDto,String> {
    List<CourseDto> findAll();
    List<CourseDto> findPaging(PagingDto paging);
    int count(PagingDto paging);
    CourseDto findById(String id);
    int deleteById(String id);
    int updateById(CourseDto dto);
    int insert(CourseDto dto);
    CourseDto findByUploadNo(int uploadNo);
}
