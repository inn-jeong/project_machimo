package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dao.CommentMapper;
import com.example.project_machimo.jolocal.admin.dto.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    SqlSession sqlSession;

    @Override
    public List<CommentVO> commentListService(int bno) {
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);
        return mCommentMapper.commentList(bno);
    }

    @Override
    public int commentInsertService(CommentVO comment) {
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);
        return mCommentMapper.commentInsert(comment);
    }

    @Override
    public int commentUpdateService(CommentVO comment) {
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);
        return mCommentMapper.commentUpdate(comment);
    }

    @Override
    public int commentDeleteService(int cno) {
        CommentMapper mCommentMapper = sqlSession.getMapper(CommentMapper.class);
        return mCommentMapper.commentDelete(cno);
    }

}
