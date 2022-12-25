package com.twy.tripwithyou_spring.service;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import com.twy.tripwithyou_spring.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReplyServiceImpl implements ReplyService {
    private ReplyMapper replyMapper;

    public ReplyServiceImpl(ReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    @Override
    public List<ReplyDto> uploadDetailList(int uploadNo) {
        return replyMapper.findByUploadNo(uploadNo);
    }

    @Override
    public int countReply(int uploadNo) {
        return replyMapper.countByUploadNo(uploadNo);
    }

    @Override
    public int countAll() {
        return replyMapper.countAll();
    }
}
