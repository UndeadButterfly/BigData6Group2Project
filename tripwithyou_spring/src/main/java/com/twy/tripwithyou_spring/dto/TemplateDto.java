package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TemplateDto {
    private int tpNo;//tp_no pk auto increment
    private String title;//title NN
    private String content;//content LongText
    private Date postdate;//postdate NN default NOW()
    private Date tpUpdate;//tp_update NN default NOW()
    private String type;
    private List<TempImgDto> tempImgList;
}
