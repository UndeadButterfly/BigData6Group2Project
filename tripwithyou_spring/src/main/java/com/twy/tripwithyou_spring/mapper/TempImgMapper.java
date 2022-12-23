package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.TemplateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TempImgMapper extends CRUD<TemplateDto, Integer> {

}
