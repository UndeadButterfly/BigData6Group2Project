package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CourseDto {
    private int courseNo;//course_no pk auto increment
    private Date startdate; //startdate NN
    private Date enddate;//enddate NN
    private int duration; //cduration NN
    private String image; //cimage NN
    private Integer budget;   //cbudget
    private int upload_no; //upload.upload_no, fk, uk NN 1:1
}
