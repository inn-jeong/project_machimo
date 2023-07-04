package com.example.project_machimo.productEnroll.service;

import com.example.project_machimo.productEnroll.dao.EnrollMapper;
import com.example.project_machimo.productEnroll.dto.ProductDto;
import com.example.project_machimo.review.dao.CommentMapper;
import com.example.project_machimo.review.dao.ReviewDao;
import com.example.project_machimo.review.dto.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class EnrollService {

    @Autowired
    private SqlSession sqlSession;

    public void write(HashMap<String, String> param) {
        log.info("@# ReviewServiceImpl.write() start");
        EnrollMapper dao = sqlSession.getMapper(EnrollMapper.class);
        dao.write(param);
        log.info("@# ReviewServiceImpl.write() end");
    }

}
