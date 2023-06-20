package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dao.ReviewDao;
import com.example.project_machimo.review.dto.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service("service")
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private SqlSession sqlSession;
    @Override
    public ArrayList<ReviewDto> list() {
        log.info("@# BServiceImpl.list() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        ArrayList<ReviewDto> list = dao.list();
        log.info("@# BServiceImpl.list() end");
        return list;
    }

    @Override
    public void write(HashMap<String, String> param) {

    }

    @Override
    public ReviewDto contentView(HashMap<String, String> param) {
        return null;
    }

    @Override
    public void modify(HashMap<String, String> param) {

    }

    @Override
    public void delete(HashMap<String, String> param) {

    }
}
