package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ZzimDto {
    private int zzimNo;//zzim_no pk auto increment
    private int courseNo;//course_no NN
    private Date zzimDate;//zzimdate NN defualt NOW()
    private String userId;//user.user_id fk NN N(zzim) : 1(user_id)
}
