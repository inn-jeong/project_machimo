package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dao.ReplyMapper;
import com.example.project_machimo.review.dto.ReplyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyMapper replyMapper;
    @Override
    public int enrollReply(ReplyDto dto) {

        int result = replyMapper.enrollReply(dto);

        return result;
    }
}
