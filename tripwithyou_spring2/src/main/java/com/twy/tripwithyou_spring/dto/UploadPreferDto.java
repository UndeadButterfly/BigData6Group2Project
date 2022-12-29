package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class UploadPreferDto {
    private int uploadPreferNo;
    private int uploadNo;
    private boolean prefer;
    private String userId;
}
