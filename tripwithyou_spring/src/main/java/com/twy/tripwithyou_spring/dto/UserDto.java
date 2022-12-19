package com.twy.tripwithyou_spring.dto;

import java.util.Date;

public class UserDto {
    private String userId; //pk user_id
    private String uname;
    private String pw;
    private String phone; //uk
    private String email; //uk
    private Date birth;
    private Date signup; //default CURRENT_TIME_STAMP()
    private Date login; //default CURRENT_TIME_STAMP()
    private int location; //거주지역
    private int hobby; //취미
    private int uType; //0 관리자, 1 유저, 2 사업자
    private int gender; //1 남성 2 여자
    private int reports; //유저의 모든 게시글의 신고 수를 합친 값
    private int loginState;
}
