package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UploadHashDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadHashMapper {
    List<UploadHashDto> findByWordForType(String word, int uptype);
    int insertOne(UploadHashDto uploadHash);
    int deleteByUploadNo(int uploadNo);
}
