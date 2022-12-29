package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.TemplateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TemplateService {
    List<TemplateDto> tList(PagingDto paging);
    int modify(TemplateDto template, int[]mdNOs);
    int remove(int tp_no);
    int register(TemplateDto template);
    List<TemplateDto> listType(String type);
}
