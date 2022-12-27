package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.UploadDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadMapper {
    List<UploadDto> findAll();

    List<UploadDto> findPaging(PagingDto paging);

    int count(PagingDto paging);

    UploadDto findById(int uploadNo);

    int deleteById(int id);

    int updateById(UploadDto dto);

    int insert(UploadDto dto);

    List<UploadDto> findByType(int upType, PagingDto paging); //추가한것

    int countByType(PagingDto paging, int upType);

    List<UploadDto> findByPrefer(PagingDto paging,int upType);// 12.25 limit rows 10으로 고정되는 것이 아니라, 페이지로 사용할 수 있게 PagingDto 추가함

    int updateViews(int uploadNo); // 12.26 조회수

    int countByUserId(PagingDto paging, String userId);

    List<UploadDto> findPagingByUserId(PagingDto paging, String userId);
}
