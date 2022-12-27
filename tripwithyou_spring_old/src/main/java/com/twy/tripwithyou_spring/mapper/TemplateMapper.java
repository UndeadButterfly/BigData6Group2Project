package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.TemplateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TemplateMapper extends CRUD<TemplateDto, Integer> {
    List<TemplateDto>findAll();
    List<TemplateDto>findPaging(PagingDto paging);
    int count(PagingDto paging);
    TemplateDto findById(Integer id);
    int deleteById(TemplateDto dto);
    int insert(TemplateDto dto);
    int updateById(TemplateDto dto);


}
