package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class UploadPreferViewDto {
    private int likes; //해당 게시글의 좋아요 수
    private int dislikes; //해당 게시글 싫어요 수
    private UploadPreferDto loginUserPrefer; //로그인 유저가 열람한 게시글에 대한 좋/싫 상태
}
