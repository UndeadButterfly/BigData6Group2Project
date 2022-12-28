package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class UploadHashDto {
    private int hashNo; //PK hash_no
    private int uploadNo; //FK Upload(uploadNo):UploadHash(uploadNo) = 1:N
    private int uptype;
    private String hashWord; //hash_word
}
