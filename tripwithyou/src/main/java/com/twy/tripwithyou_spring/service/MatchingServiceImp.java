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
    public List<MatchingDto> list(PagingDto paging) {
        int totalRows = matchingMapper.count(paging);
        paging.setTotalRows(totalRows);
        return matchingMapper.findPaging(paging);
    }

    @Override
    public List<MatchingDto> listById(PagingDto paging, String userId) {
        int totalRows = matchingMapper.countById(userId);
        paging.setTotalRows(totalRows);
        return matchingMapper.findPagingById(paging,userId);
    }

    @Override
    public List<MatchingDto> listAll() {
        return matchingMapper.findAll();
    }


    @Override
    public int register(MatchingDto match) {
        return matchingMapper.insert(match);
    }

    @Override
    public MatchingDto detail(int matchingNo){
        matchingMapper.update(new MatchingDto());
        return matchingMapper.findById(matchingNo);
    }
}