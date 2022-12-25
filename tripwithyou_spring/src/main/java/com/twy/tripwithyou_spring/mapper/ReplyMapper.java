package com.twy.tripwithyou_spring.mapper;

import com.twy.tripwithyou_spring.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper{
    int countByUploadNo(int uploadNo);

    List<ReplyDto> findByUploadNo(int uploadNo);

    List<ReplyDto> findByFkReplyNo(int replyNo);

    int insert(ReplyDto reply);

    int countAll();
}
