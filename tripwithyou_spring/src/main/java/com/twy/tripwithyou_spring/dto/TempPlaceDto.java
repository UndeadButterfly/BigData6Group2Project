package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class TempPlaceDto {
    private int tPlaceNo; //t_place_no pk auto increment
    private String name;//name NN
    private String address;//address
    private String imgPath;//img_path
    private String tel;//tel
    private String openHour;//open_hour
    private Float rate;//rate
    private int type;//type NN
    private int tpNo;//template.tp_no fk 1(template):N(tempPlace)
}
