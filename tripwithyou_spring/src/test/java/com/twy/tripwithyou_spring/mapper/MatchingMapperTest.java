package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
class MatchingMapperTest {
    @Autowired
    private MatchingMapper matchingMapper;
    @Test
    void insert() throws ParseException {
        MatchingDto matching = new MatchingDto();
        matching.setTDestination("녹번");
        matching.setTStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-12"));
        matching.setTEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-15"));
        matching.setTMember(3);
        matching.setTitle("김로아");
        matching.setLocalNo(15);
        matching.setUserId("user1");
//        matching.setDuration();
        matching.setContents("김로아 김리아");
        matchingMapper.insert(matching);
    }

    @Test
    void findById() {
       MatchingDto matchingDto= matchingMapper.findById(1);
        System.out.println(matchingDto);
    }
}