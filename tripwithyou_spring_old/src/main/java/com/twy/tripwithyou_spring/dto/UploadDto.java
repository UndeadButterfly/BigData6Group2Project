package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UploadDto {
    private int uploadNo; //PK upload_no
    private int uptype; //NN
    private String userId; //NN FK user_id
    private String title; //NN
    private String contents; //NN
    private Date postdate; //NN DEFAULT CURRENT_TIMESTAMP
    private int views; //NN DEFAULT 0
    private int likes; //NN DEFAULT 0
    private int dislikes; //NN DEFAULT 0
    private int reports; //NN DEFAULT 0
    private int upstate; //NN DEFAULT 0 | 0:없음 1:신고됨
}
