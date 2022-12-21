package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CourseDto {
    private int courseNo;
    private String userId;
    private String cTitle;
    private Date startDate;
    private Date endDate;
    private int duration;
    private int transport;
    private int members;
    private int budger;
}
