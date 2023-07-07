package com.example.project_machimo.jomuragi.review.service;

import com.example.project_machimo.jomuragi.review.dto.AttachImageVO;
import com.example.project_machimo.jomuragi.review.dao.AttachMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AttachImageService {

    @Autowired
    private SqlSession sqlSession;


//    write뷰에서 해당 리뷰아이디의 파일 삭제 가능하게끔 쿼리로 count(*)+1 reivew한걸 hidden에 담아  ajax로 넘겨줌
    public int getReviewId(){
        AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
        log.info("getReviewId.........");
        return mapper.getReviewId();
    };

    public List<AttachImageVO> getAttachList(int reviewId){
        AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
        log.info("getAttachList.........");
        return mapper.getAttachList(reviewId);
    };

    public void deleteFile(int reviewId){
        AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
        log.info("deleteFile.........");
        mapper.deleteFile(reviewId);
    };
}
