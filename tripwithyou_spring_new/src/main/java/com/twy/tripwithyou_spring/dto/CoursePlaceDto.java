package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class CoursePlaceDto {
    private int cPlaceNo;//c_place_no pk auto increment
    private String name;// name NN
    private String address;//address NN
    private String imgPath;//img_path
    private Float rate;//rate
    private int courseNo;//course.course_no fk 1(course):N(CoursePlace) NN
    private int pDay;//pday NN
    private int pOrder;//porder NN
    private String memo;//memo mediumtext

}
