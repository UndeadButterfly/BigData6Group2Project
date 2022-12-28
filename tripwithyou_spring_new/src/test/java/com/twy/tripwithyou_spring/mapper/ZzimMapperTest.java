package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.ZzimDto;
import com.twy.tripwithyou_spring.dto.ZzimViewDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ZzimMapperTest {
    @Autowired
    private ZzimMapper zzimMapper;
    private static int insertZzimNo;
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Order(0)
    @Test
    void countByCourseNoAndZzim() {
        int zzimCount=zzimMapper.countByCourseNoAndZzim(1);
        log.info("1번글의 찜 수: "+zzimCount);
    }
    @Order(1)
    @Test
    void insert() {
        ZzimDto zzimDto = new ZzimDto();
        zzimDto.setUserId("user1");
        zzimDto.setCourseNo(4);
        zzimMapper.insert(zzimDto);
        insertZzimNo=zzimDto.getZzimNo();
        log.info("등록된 찜 번호:"+insertZzimNo);
    }
    @Order(3)
    @Test
    void delete() {
       log.info("user1이 찜 취소:"+zzimMapper.delete(insertZzimNo));

    }
    @Order(2)
    @Test
    void findByCourseNoAndUserId() {
       log.info("user1이 courseNo4 찜하기 했을 때:"+zzimMapper.findByCourseNoAndUserId(4,"user1").toString());
    }
}