package com.twy.tripwithyou_spring.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ReplyDto {
    private int replyNo;//reply_no
    private int uploadNo;//upload_no
    private String userId;//user_id
    private Integer fkReplyNo;//fk_reply_no
    private String rContent;//rcontent
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date rDate=new Date();//rdate
    private List<ReplyDto> replyList;//대댓글
    private UserDto user;
}
