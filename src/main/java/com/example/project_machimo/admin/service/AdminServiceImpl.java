package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.AdminDao;
import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.ProductDto;
import com.example.project_machimo.admin.dto.UsersDto;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void adminDelete(int user_id) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.adminDelete(user_id);
    }

    @Override
    public void adminModify(int user_id) {
        System.out.println("@# service adminModify userid= "+ user_id);
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.adminModify(user_id);
    }


    @Override
    public UsersDto userView(HashMap<String, String> param) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.userView(param);
    }
    @Override
    public void boardModify(BoardDto dto) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardModify(dto);
    }

    @Override
    public void boardDelete(int board_id) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardDelete(board_id);
    }




    @Override
    public ArrayList<BoardDto> boardList(Criteria cri) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.BoardList(cri);
    }

    @Override
    public void boardWrite(BoardDto dto) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.boardWrite(dto);
    }

    @Override
    public Integer updateHits(int board_id) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.updateHits(board_id);
    }

    @Override
    public BoardDto boardView(int board_id) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.boardView(board_id);
    }


    /////////제품관리/////////
    @Override
    public ArrayList<ProductDto> pList(Criteria cri) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.pList(cri);
    }
}

