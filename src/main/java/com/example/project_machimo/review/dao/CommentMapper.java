package com.example.project_machimo.review.dao;

import com.example.project_machimo.review.dto.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Repository("com.example.project_machimo.review.dao.CommentMapper")
public interface CommentMapper {
    // 댓글 개수
    public int commentCount() throws Exception;

    // 댓글 목록
//    public List<CommentVO> commentList() throws Exception;
    public List<CommentVO> commentList(int bno) throws Exception;

    // 댓글 작성
    public int commentInsert(CommentVO comment) throws Exception;

    // 댓글 수정
    int commentUpdate(CommentVO comment) throws Exception;

    // 댓글 삭제
    public int commentDelete(int cno) throws Exception;

}