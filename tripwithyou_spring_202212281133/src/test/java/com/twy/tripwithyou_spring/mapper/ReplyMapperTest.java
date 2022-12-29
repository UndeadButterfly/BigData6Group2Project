package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReplyMapperTest {
    @Autowired
    private ReplyMapper replyMapper;
    @Test
    void countByUploadNo() {
        int a = replyMapper.countByUploadNo(1);
        System.out.println(a);
    }

    @Test
    void findByUploadNo() {
    }

    @Test
    void findByFkReplyNo() {
        List<ReplyDto> rer =replyMapper.findByFkReplyNo(4);
        System.out.println(rer);
    }

    @Test
    void insert() {
    }

    @Test
    void countAll() {
    }

}