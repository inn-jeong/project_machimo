package com.example.project_machimo.review.controller;

import com.example.project_machimo.review.dto.CommentDto;
import com.example.project_machimo.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class CommentController {
    @Autowired
    private ReviewService service;

    @PostMapping("/board/comment/write")
    private String insertComment(@RequestParam("reviewId") int reviewId, @RequestParam("content") String content) throws Exception{
        CommentDto dto = new CommentDto();
        dto.setContent(content);
        dto.setReviewId(reviewId);
        service.insertComment(dto);
        String redirect_url = "redirect:/review/content_view?reviewId="+Integer.toString(reviewId);
        return redirect_url;
    }

    @GetMapping("/getCommentList")
    @ResponseBody
    private List<CommentDto> getCommentList(@RequestParam("reviewId") int reviewId)throws Exception{
        CommentDto dto = new CommentDto();
        dto.setReviewId(reviewId);
        return service.getCommentList(dto);
    }
}
