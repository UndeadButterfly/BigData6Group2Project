package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
@SpringBootTest
class MatchingServiceTest {
    @Autowired
    private MatchingService matchingService;
    @Test
    void register() throws ParseException {
        MatchingDto matching = new MatchingDto();
        matching.setTMember(3);
        matching.setTEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-12"));
        matching.setTStart(new SimpleDateFormat("yyyy-MM-dd").parse("2000-12-12"));
        matching.setUserId("user1");
        matching.setTitle("rtrtr");
        matching.setLocalNo("10");
        matching.setContents("fefef");
        matching.setTDestination("fefef");
        matchingService.register(matching);
    }
}