package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.PagingDto;
import com.twy.tripwithyou_spring.dto.TempImgDto;
import com.twy.tripwithyou_spring.dto.TemplateDto;
import com.twy.tripwithyou_spring.mapper.TemplateMapper;
import com.twy.tripwithyou_spring.mapper.UploadMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TemplateServiceImp implements TemplateService{
    private TemplateMapper templateMapper;
    private UploadMapper uploadMapper;

    public TemplateServiceImp(TemplateMapper templateMapper, UploadMapper uploadMapper){
        this.templateMapper = templateMapper;
        this.uploadMapper = uploadMapper;

    }

    /*
    * tempimgservice 만들기
    * 서비스에 listTempImg(tpNo) return List<tempimgdto> 만들기
    * mapper tempimgmapper 만들기
    * mapper에 findbytpno(tpNo) return List<tempimgdto> 만들기
    * */

    public List<TemplateDto>tList(){
        List<String>orderFields = new ArrayList<>();
        orderFields.add("upload_no"); // 최신 순
        List<TemplateDto>tList=new ArrayList<>();
        return tList;
    }

    @Override
    public List<TemplateDto> tList(PagingDto paging) {
        int totalRows = templateMapper.count(paging);
        paging.setTotalRows(totalRows);
        return templateMapper.findPaging(paging);
    }

    @Override
    public int modify(TemplateDto template, int[] mdNOs) {
        return 0;
    }

    @Override
    public int remove(int tp_no) {
        return 0;
    }

    @Override
    public int register(TemplateDto template) {
        return 0;
    }
    public List<TemplateDto> listType(String type) {
        return templateMapper.findByType(type);
    }
}
