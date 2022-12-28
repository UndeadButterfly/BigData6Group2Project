package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UploadImgDto;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UploadImgMapper{

    UploadImgDto findByUploadNo(int uploadNo);
    UploadImgDto findById(int uploadImgNo);
    int deleteOne(int uploadImgNo);
    int insertOne(UploadImgDto dto);
}
