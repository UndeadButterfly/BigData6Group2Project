package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;

import java.util.List;

public interface MatchingService {
    int register(MatchingDto match);

    MatchingDto detail(int matchNo);

    List<MatchingDto> list(PagingDto paging);
    List<MatchingDto> listById(PagingDto paging, String userId);

    List<MatchingDto> listAll();
}