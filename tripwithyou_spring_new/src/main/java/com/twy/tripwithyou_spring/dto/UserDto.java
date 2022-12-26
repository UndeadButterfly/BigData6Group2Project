package com.twy.tripwithyou_spring.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDto {
    @DateTimeFormat
    LocalDate date = LocalDate.now();
    private String userId; //pk user_id
    private String pw;
    private String name;
    private String uimg;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private int ages;
    private String phone; //uk
    private int gender; //0 남성 1 여자
    private String email; //uk
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signup; //default CURRENT_TIME_STAMP()
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date login; //default CURRENT_TIME_STAMP()
    private String  location; //거주지역
    private String hobby; //취미
    private int uType; //0 관리자, 1 유저, 2 사업자
    private int reports; //유저의 모든 게시글의 신고 수를 합친 값
    private int loginState;

    public void setAges() {
        this.ages = date.getYear() - this.birth.getYear() + 1;
    }
}