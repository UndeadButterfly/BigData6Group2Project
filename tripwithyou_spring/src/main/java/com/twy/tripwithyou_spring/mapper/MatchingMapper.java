package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MatchingMapper extends CRUD<MatchingDto,String>{
}
