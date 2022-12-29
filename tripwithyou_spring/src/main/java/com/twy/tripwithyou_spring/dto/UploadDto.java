package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UploadDto {
    private int uploadNo;
    private int upType;
    private String userId;
    private String title;
    private String contents;
    private Date postdate; //NN DEFAULT CURRENT_TIMESTAMP
    private int views;
    private int likes;
    private int hates;
    private int reports;
    private int upstate; //NN DEFAULT 0 | 0:없음 1:신고됨
    private List<UploadImgDto> uploadImgList;
    private List<ReplyDto> replyList;
    private UploadPreferViewDto uploadPreferView; // 12.28 detail에 출력될 좋/싫 내역
    private UserDto user; //12.28 유저 정보 불러오기
}
