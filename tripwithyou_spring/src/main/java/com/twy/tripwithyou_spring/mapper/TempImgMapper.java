package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.TempImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TempImgMapper {
    List<TempImgDto>findAll();
    List<TempImgDto>findByTpNo(int tpNo);
    int deleteByTpNo(int tpNo);
    int insert(TempImgDto temImg);
}
