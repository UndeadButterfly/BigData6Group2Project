package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class UploadDto {
    private int upload_no;
    private int upType;
    private String user_id;
    private String title;
    private String contents;
    private int views;
    private int likes;
    private int hates;
    private int reports;
    private List<UploadImgDto> uploadImgList;
}
