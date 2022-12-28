package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CourseDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.ZzimViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends CRUD<CourseDto,String> {
    List<CourseDto> findAll();
    List<CourseDto> findPaging(PagingDto paging);
    int count(PagingDto paging);
    CourseDto findById(Integer id);
    int deleteById(Integer id);
    int updateById(CourseDto dto);
    int insert(CourseDto dto);
    CourseDto findByUploadNo(int uploadNo);

    List<CourseDto> findPagingByUserId(PagingDto pagingDto, String userId);

    int countById(String userId);
    ZzimViewDto countZzimById(int courseNo);
}
