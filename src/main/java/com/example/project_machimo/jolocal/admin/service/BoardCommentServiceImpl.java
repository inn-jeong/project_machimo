package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dao.BoardCommentMapper;
import com.example.project_machimo.jolocal.admin.dto.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCommentServiceImpl implements BoardCommentService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<CommentVO> commentListService(int bno) {
        BoardCommentMapper mBoardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return mBoardCommentMapper.commentList(bno);
    }

    @Override
    public int commentInsertService(CommentVO comment) {
        BoardCommentMapper mBoardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return mBoardCommentMapper.commentInsert(comment);
    }

    @Override
    public int commentUpdateService(CommentVO comment) {
        BoardCommentMapper mBoardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return mBoardCommentMapper.commentUpdate(comment);
    }

    @Override
    public int commentDeleteService(int cno) {
        BoardCommentMapper mBoardCommentMapper = sqlSession.getMapper(BoardCommentMapper.class);
        return mBoardCommentMapper.commentDelete(cno);
    }

}
