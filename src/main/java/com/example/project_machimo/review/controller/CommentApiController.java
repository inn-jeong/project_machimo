package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.CommentRequest;
import com.example.project_machimo.review.dto.CommentResponse;
import com.example.project_machimo.review.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    // 신규 댓글 생성
//    @PostMapping("/review/{reviewId}/comments")
    @PostMapping("/review/{reviewId}/comments")
//    @RequestMapping("/review/{reviewId}/comments")
    public CommentResponse saveComment(@PathVariable final Long reviewId,
                                       @RequestBody final CommentRequest params) {
        log.info("params"+params.getId());
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }
}
