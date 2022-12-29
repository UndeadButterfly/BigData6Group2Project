package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@SpringBootTest
class MatchingMapperTest {
    @Autowired
    private MatchingMapper matchingMapper;
    @Test
    void insert() throws ParseException {
        MatchingDto matching = new MatchingDto();
        matching.setTDestination("제주");
        matching.setTStart(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-12"));
        matching.setTEnd(new SimpleDateFormat("yyyy-MM-dd").parse("2020-12-15"));
        matching.setTMember(1);
        matching.setTitle("제목");
        matching.setLocalNo("1");
        matching.setUserId("user1");
        matching.setDuration();
        matchingMapper.insert(matching);
    }

    @Test
    void findPagingById() {
        PagingDto paging = new PagingDto(1, 10, "matching_no", "desc");
        List<MatchingDto> matchingList = matchingMapper.findPagingById(paging,"user1");
        System.out.println(matchingList);
    }
}