package com.example.project_machimo.review.dao;

import com.example.project_machimo.review.dto.ReplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
//    댓글 등록
    public int enrollReply(ReplyDto dto);

}

