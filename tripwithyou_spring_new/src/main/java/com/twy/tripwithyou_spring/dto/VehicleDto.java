package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class VehicleDto {
    private int vehicleNo;//vehicle_no pk auto increment
    private int courseNo;//course.course_no fk N(vehicle):1(course) NN
    private int vDay;//vday NN
    private int vOrder;//vorder NN
    private String vType;//vtype NN
    private String memo;//memo mediumtext
}
