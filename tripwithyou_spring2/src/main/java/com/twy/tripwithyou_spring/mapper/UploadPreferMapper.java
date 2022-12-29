package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UploadPreferDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadPreferMapper {
    int countByUploadPreferIsTrue(int uploadNo);
    int countByUploadPreferIsFalse(int uploadNo);
    int insert(UploadPreferDto uploadPrefer);
    int update(UploadPreferDto uploadPrefer);
    int delete(int uploadPreferNo);
    UploadPreferDto findByUploadNoAndUserId(int uploadNo,String userId);
}
