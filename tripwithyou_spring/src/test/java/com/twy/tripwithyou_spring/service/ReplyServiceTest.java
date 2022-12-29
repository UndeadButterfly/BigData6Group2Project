package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyServiceTest {
    @Autowired
    private ReplyService replyService;
    @Test
    void registerOne() {
        ReplyDto reply = new ReplyDto();
        reply.setUploadNo(10);
        reply.setUserId("user8");
        reply.setFkReplyNo(null);
        reply.setRContent("테스트하고 지울거임");
        replyService.registerOne(reply);
    }
}