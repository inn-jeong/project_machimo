package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

public interface ReplyService {
    /* 댓글 등록 */
    public int enrollReply(ReplyDto dto);
}
