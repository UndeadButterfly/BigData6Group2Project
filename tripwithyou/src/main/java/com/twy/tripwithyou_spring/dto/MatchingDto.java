package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
@Data
public class MatchingDto extends UploadDto{
    private int tripNo;
    private int uploadNo;
    private String arrival; //목적지
    private Date startDate; //출발일
    private Date endDate; //도착일
    private int duration; //일정
    private int members; //멤버 변수
    
}
