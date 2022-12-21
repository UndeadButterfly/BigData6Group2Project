package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper extends CRUD<UploadDto,String> {
    List<UploadDto> findAll();
    List<UploadDto> findPaging(PagingDto paging);
    int count(PagingDto paging);
    UploadDto findById(String id);
    List<UploadDto> findByType(int type, PagingDto paging); //추가한것
    int deleteById(String id);
    int updateById(UploadDto dto);
    int insert(UploadDto dto);
}
