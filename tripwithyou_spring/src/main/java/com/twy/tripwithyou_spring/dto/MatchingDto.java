package com.twy.tripwithyou_spring.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MatchingDto extends UploadDto {
    private int matchingNo;
    private String title;
    private String userId; //작성자
    private String tDestination; //목적지
    private String matchingImgPath; //매칭 이미지
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tStart; //출발일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tEnd; //도착일
    private int localNo; //지역 번호
    private int tMember; //매칭 인원 수
    private int duration; //일정

//    public MatchingDto() {
//        this.duration = (int) ((tEnd.getTime() - tStart.getTime()) * 60 * 60 * 24);
//    }
}
