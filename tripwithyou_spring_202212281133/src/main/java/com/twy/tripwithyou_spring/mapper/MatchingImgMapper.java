package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MatchingImgMapper {
    List<MatchingImgDto> findByMatchingNo(int matchingNo);
    MatchingImgDto findOne(int matchingImgNo);
    int insertOne(MatchingImgDto dto);
}
