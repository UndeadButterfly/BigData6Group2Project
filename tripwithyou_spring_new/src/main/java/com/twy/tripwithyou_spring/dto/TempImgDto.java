package com.twy.tripwithyou_spring.dto;

import lombok.Data;

@Data
public class TempImgDto {
    private int tpImgNo;//tp_img_no pk auto increment
    private String imgPath;//img_path
    private int tpNo;//template.tp_no fk NN 1(template):N(template_img)

    // 검색 필터
    private String keyword; // 검색내용
}
