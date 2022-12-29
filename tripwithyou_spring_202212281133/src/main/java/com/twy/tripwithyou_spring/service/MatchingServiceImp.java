package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.MatchingImgDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.mapper.MatchingImgMapper;
import com.twy.tripwithyou_spring.mapper.MatchingMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatchingServiceImp implements MatchingService {
    private MatchingMapper matchingMapper;
    private MatchingImgMapper matchingImgMapper;
    @Value("${img.upload.path}")
    private String imgPath;

    public MatchingServiceImp(MatchingMapper matchingMapper, MatchingImgMapper matchingImgMapper) {
        this.matchingMapper = matchingMapper;
        this.matchingImgMapper = matchingImgMapper;
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
        int register=0;
        register+=matchingMapper.insert(match);
        if(match.getMatchingImgList()!=null){
            for (MatchingImgDto matchingImg:match.getMatchingImgList()){
                matchingImg.setMatchingNo(match.getMatchingNo());
                register+=matchingImgMapper.insertOne(matchingImg);
            }
        }
        return register;
    }

    @Override
    public MatchingDto detail(int matchNo){
        return matchingMapper.findById(matchNo);
    }
}