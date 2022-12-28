package com.twy.tripwithyou_spring.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class MatchingDto {
    private int matchingNo;
    private String title; // 매칭 제목
    private String contents; //내용
    private String userId; //작성자
    private String tDestination; //목적지
    private String matchingImgPath; //매칭 이미지
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tStart; //출발일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tEnd; //도착일
    private String localNo; //지역 번호
    private int tMember; //매칭 인원 수
    private int duration; //일정
    private List<MatchingImgDto> matchingImgList;
    public void setDuration() {
        this.duration = (int) ((tEnd.getTime() - tStart.getTime()) * 60 * 60 * 24)+1;
    }
}
