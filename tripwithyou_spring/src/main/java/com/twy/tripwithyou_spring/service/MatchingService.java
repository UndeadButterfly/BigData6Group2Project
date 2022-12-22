package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;

import java.util.List;

public interface MatchingService {
    int register(MatchingDto Match) throws Exception;

    MatchingDto detail(int matchNo) throws Exception;

    List<MatchingDto> list(PagingDto paging) throws Exception;

}