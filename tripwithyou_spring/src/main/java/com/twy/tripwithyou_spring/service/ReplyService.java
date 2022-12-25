package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    List<ReplyDto> uploadDetailList(int uploadNo);
    int countReply(int uploadNo);
    int countAll();
}
