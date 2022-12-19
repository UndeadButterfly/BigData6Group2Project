package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UploadDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadMapper extends CRUD<UploadDto,String> {
}
