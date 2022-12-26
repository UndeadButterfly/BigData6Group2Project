package com.twy.tripwithyou_spring.dto;

public class UploadImgDto {
    private int uploadImgNo;//upload_img_no pk auto increment
    private int uploadNo;//upload_no fk 1(upload):N(uploadImg)
    private String imgPath;//img_path
}
