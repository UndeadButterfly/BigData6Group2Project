package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.TempPlaceDto;
import com.twy.tripwithyou_spring.dto.TemplateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TempPlaceMapper {
    List<TempPlaceDto>findAll();
    List<TempPlaceDto>findByTpNo(int tpNo);
    int deleteByTpNo(int tpNo);
    int insert(TempPlaceDto tempPlace);
}
