package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.mapper.MatchingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MatchingServiceImpTest {
    @Autowired
    private MatchingMapper matchingMapper;

    @Test
    void listById() {
        PagingDto paging = new PagingDto(1,10,"matching_no","DESC");
        List<MatchingDto> matchingDtoList = matchingMapper.findPagingById(paging,"user1");
        System.out.println(matchingDtoList);
    }
}