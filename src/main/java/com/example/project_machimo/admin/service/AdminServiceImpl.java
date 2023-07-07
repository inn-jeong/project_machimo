package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.AdminDao;
import com.example.project_machimo.admin.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService  {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<UsersDto> adminList(Criteria cri) {
        log.info("@# AdminServiceImpl adminList start");
        log.info("@# cri.pageNum" + cri.getPageNum() +"@# cri.getAmount" + cri.getAmount());
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.adminList(cri);
    }

    @Override
    public int getTotalCount() {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.getTotalCount();
    }

    @Override
    public UsersDto userView(int userId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.userView(userId);
    }

    @Override
    public void removeReport(int reportId, int userId, int productId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.removeReport(reportId, userId, productId);
    }

    @Override
    public void adminDelete(int userId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.adminDelete(userId);
    }

    @Override
    public void adminModify(int userId) {
        System.out.println("@# service adminModify userid= "+ userId);
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.adminModify(userId);
    }



    @Override
    public void boardDelete(int boardId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardDelete(boardId);
    }

    @Override
    public BoardDto boardModifyView(int boardId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.boardModifyView(boardId);
    }

    @Override
    public void boardModify(BoardDto dto) {
        System.out.println("@# service boardModify");
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardModify(dto);
    }


    @Override
    public ArrayList<BoardDto> boardList(Criteria cri) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.BoardList(cri);
    }
    @Override
    public BoardDto boardView(int boardId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.boardView(boardId);
    }

    @Override
    public void boardWrite(BoardDto dto) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardWrite(dto);
    }

    @Override
    public void updateHits(int boardId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.updateHits(boardId);
    }



    /////////제품관리/////////
    @Override
    public ArrayList<ProductDto> pList(Criteria cri) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.pList(cri);
    }


    @Override
    public void productDelete(int productId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.productDelete(productId);
    }

}

