package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchingMapperTest {
    @Autowired
    private MatchingMapper matchingMapper;
    private Logger log= LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Test
    void findAll() {
    }

    @Test
    void findByStartAndEnd() {
    }

    @Test
    @DateTimeFormat
    void insert() throws ParseException {
        MatchingDto matching=new MatchingDto();
        matching.setTDestination("제주도");
        matching.setTMember(5);
        matching.setTStart(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-20"));
        matching.setTEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2022-12-25"));
        matching.setTitle("trip");
        matching.setLocalNo(10);
        matching.setUserId("user1");
        matchingMapper.insert(matching);
        log.info(matching.toString());
    }
}