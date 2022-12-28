package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.UploadHashDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UploadHashMapperTest {
    @Autowired
    private UploadHashMapper uploadHashMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    void findByWord() {
        log.info(uploadHashMapper.findByWordForType("%목%", 2).toString());
    }

    @Test
    void insertOne() {
        UploadHashDto uploadHash = new UploadHashDto();
        uploadHash.setUploadNo(1);
        uploadHash.setHashWord("목적1");
        uploadHash.setUptype(2);
        int insert = uploadHashMapper.insertOne(uploadHash);
    }
}