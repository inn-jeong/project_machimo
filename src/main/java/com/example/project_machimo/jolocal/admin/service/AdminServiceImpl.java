package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.jolocal.admin.dao.AdminDao;
import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import com.example.project_machimo.jolocal.admin.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public UsersDto reportView(int userId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        return dao.reportView(userId);
    }

//    @Override
//    public void removeReport(int reportId, int userId, int productId) {
//        AdminDao dao = sqlSession.getMapper(AdminDao.class);
//        dao.removeReport(reportId, userId, productId);
//    }

    @Override
    public void adminDelete(int userId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.adminDelete(userId);
    }

    @Override
    public void Authorization(int userId) {
        System.out.println("@# service adminModify userid= "+ userId);
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.Authorization(userId);
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

    @Override
    public void removeReport(int userId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.removeReport(userId);
    }

    @Override
    public int loginUser(int boardId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
         return dao.loginUser(boardId);



//        1 admin 0 user
//        int loginUser = -1;
//        if(loginUser == 1){ //admin
//            return 1;
//        } else if (loginUser == 0) { //user
//            return 0;
//        } else {
//            return -1; //login 필요
//        }
    }

}

