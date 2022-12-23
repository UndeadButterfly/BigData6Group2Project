package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.CoursePlaceDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoursePlaceMapperTest {

    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CoursePlaceMapper coursePlaceMapper;

    @Test
    void findAll() {
        log.info(coursePlaceMapper.findAll().toString());
    }

    @Test
    void findByCourseNo() {
        log.info(coursePlaceMapper.findByCourseNo(1).toString());
    }

    @Test
    void deleteByCourseNo() {
        log.info(coursePlaceMapper.deleteByCourseNo(31)+"");
    }

    @Test
    void insert() {
        CoursePlaceDto coursePlace = new CoursePlaceDto();
        coursePlace.setName("장소 테스트");
        coursePlace.setAddress("주소 테스트");
        coursePlace.setImgPath("사진 경로 테스트");
        coursePlace.setTel("000-0000-0000");
        coursePlace.setOpenHour("00:00~10:00");
        coursePlace.setRate((float)4.5);
        coursePlace.setType("testingType");
        coursePlace.setCourseNo(1);
        coursePlace.setPDay(3);
        coursePlace.setPOrder(1);
        coursePlace.setMemo("테스트 용 장소입니다");
        log.info(coursePlaceMapper.insert(coursePlace)+"");
    }
}