package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.mapper.MatchingMapper;

import java.util.List;

public class MatchingServiceImp implements MatchingService{
    private MatchingMapper matchingMapper;

    public MatchingServiceImp(MatchingMapper matchingMapper){
        this.matchingMapper = matchingMapper;
    }

    @Override
    public List<MatchingDto> list(PagingDto paging) throws Exception{
        int totalRows=matchingMapper.count(paging);
        paging.setTotalRows(totalRows);
        return matchingMapper.findPaging(paging);
    }

    @Override
    public MatchingDto detail(String matchNo) throws Exception {
        return matchingMapper.findById(matchNo);
    }

    @Override
    public int register(MatchingDto Match){
        return 0;
    }
}
