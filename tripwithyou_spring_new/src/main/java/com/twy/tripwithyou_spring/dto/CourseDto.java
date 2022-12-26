package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CourseDto {
    private int courseNo; //PK course_no
    private Date startdate; //NN
    private Date enddate; //NN
    private int duration; //NN
    private String image;
    private int budget;
    private int uploadNo; //FK UK NN upload_no
    private UploadDto uploadDto;
    private List<CoursePlaceDto> coursePlaceList;
    private List<VehicleDto> vehicleList;
}