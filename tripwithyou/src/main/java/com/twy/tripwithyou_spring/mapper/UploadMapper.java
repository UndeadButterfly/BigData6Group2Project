package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper{
    List<UploadDto> findAll();

    List<UploadDto> findPaging(PagingDto paging);

    int count(PagingDto paging);

    UploadDto findById(int id);

    int deleteById(String id);

    int updateById(UploadDto dto);

    int insert(UploadDto dto);
}
