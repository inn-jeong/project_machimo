package com.example.project_machimo.jomuragi.review.service;

import com.example.project_machimo.jomuragi.review.dao.CommentMapper;
import com.example.project_machimo.jomuragi.review.dto.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private SqlSession sqlSession;

    public List<CommentVO> commentListService(int bno) throws Exception{
//    public List<CommentVO> commentListService() throws Exception{
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);

        return mCommentMapper.commentList(bno);
//        return mCommentMapper.commentList();
    }

    public int commentInsertService(CommentVO comment) throws Exception{
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);

        return mCommentMapper.commentInsert(comment);
    }

    public int commentUpdateService(CommentVO comment) throws Exception{
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);

        return mCommentMapper.commentUpdate(comment);
    }

    public int commentDeleteService(int cno) throws Exception{
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);

        return mCommentMapper.commentDelete(cno);
    }
}