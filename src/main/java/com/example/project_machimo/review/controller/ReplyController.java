package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.ReplyDto;
import com.example.project_machimo.review.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//뷰 만들지 않고 http body 바로 데이터 담아 반환하므로 RestController
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    /* 댓글 등록 */
    @PostMapping("/enroll")
    public void enrollReplyPOST(ReplyDto dto) {
        replyService.enrollReply(dto);
    }
}
