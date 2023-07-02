package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.CommentRequest;
import com.example.project_machimo.review.dto.CommentResponse;
import com.example.project_machimo.review.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/review/{reviewId}/comments")
    public CommentResponse saveComment(@PathVariable final int reviewId, @RequestBody final CommentRequest params) {
        int id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }
}
