package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.mapper.MatchingMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatchingServiceImp implements MatchingService {
    private MatchingMapper matchingMapper;

    public MatchingServiceImp(MatchingMapper matchingMapper) {
        this.matchingMapper = matchingMapper;
    }

    @Override
    public List<MatchingDto> list(PagingDto paging){
        int totalRows = matchingMapper.count(paging);
        paging.setTotalRows(totalRows);
        return matchingMapper.findPaging(paging);
    }

    @Override
    public List<MatchingDto> listAll() {
        return matchingMapper.findAll();
    }


    @Override
    public int register(MatchingDto Match) {
        return matchingMapper.insert(Match);
    }

    @Override
    public MatchingDto detail(int matchNo){
        return matchingMapper.findById(matchNo);
    }
}