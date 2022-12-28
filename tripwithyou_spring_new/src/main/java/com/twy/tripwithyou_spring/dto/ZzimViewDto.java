package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class ZzimViewDto {
    private int Zzim; //countByCourseNoandZzimIsTrue(coureNo)
    private ZzimDto loginUserZzim; //findByCourseNoAndUserId(courseNo,userId)
}
