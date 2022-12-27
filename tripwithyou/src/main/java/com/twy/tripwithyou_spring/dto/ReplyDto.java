package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReplyDto {
    private int replyNo;//reply_no
    private int uploadNo;//upload_no
    private String userId;//user_id
    private int fkReplyNo;//fk_reply_no
    private String rContent;//rcontent
    private Date rDate;//rdate
    private List<ReplyDto> replyList;//대댓글
}
