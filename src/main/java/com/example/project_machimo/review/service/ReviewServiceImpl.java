package com.example.project_machimo.review.service;

import com.example.project_machimo.review.dao.ReviewDao;
import com.example.project_machimo.review.dto.Criteria;
import com.example.project_machimo.review.dto.ReplyDto;
import com.example.project_machimo.review.dto.ReviewDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<ReviewDto> list() {
        log.info("@# ReviewServiceImpl.list() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        ArrayList<ReviewDto> list = dao.list();
        log.info("@# ReviewServiceImpl.list() end");
        return list;
    }

    @Override
    public ArrayList<ReviewDto> list(Criteria cri) {
        log.info("@# BServiceImpl.list(Criteria cri) start");
        log.info("@# cri =========>"+cri);

        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);

        log.info("@# BServiceImpl.list(Criteria cri) end");

        return dao.listWithPaging(cri);
    }

    @Override
    public void write(HashMap<String, String> param) {
        log.info("@# ReviewServiceImpl.write() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        dao.write(param);
        log.info("@# ReviewServiceImpl.write() end");
    }

    @Override
    public ReviewDto contentView(HashMap<String, String> param) {
        log.info("@# ReviewServiceImpl.contentView() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        ReviewDto dto = dao.contentView(param);
        log.info("@# ReviewServiceImpl.contentView() end");
        return dto;
    }

    @Override
    public void modify(HashMap<String, String> param ) {
        log.info("@# ReviewServiceImpl.modify() start");

        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);

        dao.modify(param);
        log.info("@# ReviewServiceImpl.modify() end");

    }

//    @Override
//    public String modify_view(HashMap<String, String> param) {
//        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
//        dao.modify_view(param);
//    }


    @Override
    public ReviewDto modify_view(String reviewId) {
        log.info("@# ReviewServiceImpl.modify_view() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        ReviewDto dto = dao.modify_view(reviewId);
        log.info("@# ReviewServiceImpl.modify_view() end");
        return dto;
    }

    @Override
//    어차피 맞는 번호의 값을 삭제만 하면 되니까 hashmap필요없고
//    String 으로 Id값만 받아옴(int가 맞지만.. 글 번호 삭제만 하면 됨)
    public void delete(String reviewId) {
        log.info("@# ReviewServiceImpl.delete() start");
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        dao.delete(reviewId);
        log.info("@# ReviewServiceImpl.delete() end");
    }

    @Override
    public int getTotalCount() {
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        return dao.getTotalCount();
    }

    @Override
    public void updateCount(int reviewId) {
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        dao.updateCount(reviewId);
    }

/////////////////////////////////////////댓글/////////////////////////////////////////

    @Override
    public boolean addReply(HashMap<String, String> param) {
        return false;
    }
//    @Override
//    public boolean addReply(ReplyDto r) {
//        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
//        return dao.addReply(r);
//    }

    @Override
    public List<ReplyDto> getReply(int boardId) {
        ReviewDao dao = sqlSession.getMapper(ReviewDao.class);
        return dao.getReply(boardId);
    }


    }
