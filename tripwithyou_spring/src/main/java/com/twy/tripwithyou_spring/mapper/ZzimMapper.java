package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.ZzimDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZzimMapper {
    int countByCourseNoAndZzim(int courseNo);
    int insert(ZzimDto zzim);
    int delete(int zzimNo);
    ZzimDto findByCourseNoAndUserId(int courseNo, String userId);
}
