package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.MatchingDto;
import com.twy.tripwithyou_spring.dto.PagingDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface MatchingMapper extends CRUD<MatchingDto,Integer>{
    @Override
    List<MatchingDto> findAll();

    @Override
    List<MatchingDto> findPaging(PagingDto paging);

    List<MatchingDto> findByStartAndEnd(Date tStart,Date tEnd);
    @Override
    int count(PagingDto paging);

    @Override
    MatchingDto findById(Integer id);

    @Override
    int deleteById(Integer id);

    @Override
    int update(MatchingDto dto);

    @Override
    int insert(MatchingDto dto);

    int countById(String userId);

    List<MatchingDto> findPagingById(PagingDto paging, String userId);
}
