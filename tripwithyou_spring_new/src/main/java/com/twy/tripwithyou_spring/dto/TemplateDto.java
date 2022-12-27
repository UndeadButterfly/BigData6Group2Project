package com.twy.tripwithyou_spring.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TemplateDto {
    private int tpNo;//tp_no pk auto increment
    private String title;//title NN
    private String content;//content LongText
    private Date postdate;//postdate NN default NOW()
    private Date tpUpdate;//tp_update NN default NOW()
    private int uploadNo; //FK UK NN upload_no
    private UploadDto uploadDto;
}
