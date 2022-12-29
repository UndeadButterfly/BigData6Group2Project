package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyServiceImplTest {
    @Autowired
    private ReplyService replyService;
    @Test
    void modifyOne() {
        ReplyDto reply = new ReplyDto();
        reply.setReplyNo(22);
        reply.setUploadNo(10);
        reply.setUserId("user1");
        reply.setRContent("서비스 테스트");
        replyService.modifyOne(reply);
    }
}