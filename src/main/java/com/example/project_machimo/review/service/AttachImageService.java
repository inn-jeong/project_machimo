//package com.example.project_machimo.review.service;
//
//import com.example.project_machimo.review.dto.AttachImageVO;
//import com.example.project_machimo.review.dao.AttachMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Slf4j
//public class AttachImageService {
//
//    @Autowired
//    private SqlSession sqlSession;
//
//    public List<AttachImageVO> getAttachList(int reviewId){
//        AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
//        log.info("getAttachList.........");
//        return mapper.getAttachList(reviewId);
//    };
//
//}
