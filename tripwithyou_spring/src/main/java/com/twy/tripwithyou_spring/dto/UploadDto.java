package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class UploadDto {
    private int uploadNo;
    private int upType;
    private String userId;
    private String uTitle;
    private String contents;
    private int views;
    private int likes;
    private int hates;
    private int reports;
}
