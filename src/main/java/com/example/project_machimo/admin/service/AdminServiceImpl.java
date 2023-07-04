package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.AdminDao;
import com.example.project_machimo.admin.dto.BoardDto;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.ProductDto;
import com.example.project_machimo.admin.dto.UsersDto;
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

    public void save(ProductDto dto) throws IOException {
        int fileStatus = dto.getIId();
        //파일 첨부 여부에 따라 로직 분리
        if(dto.getBoardFile().isEmpty()){
            //첨부 파일 없는 경우
            fileStatus = 0;
        } else {
            //첨부 파일 있는 경우
            fileStatus = 1;
            MultipartFile boardFile = dto.getBoardFile();//dto에 실제 파일 가져옴
            String originalFilename = boardFile.getOriginalFilename(); //실제 파일의 이름 가져옴
            String iImage = System.currentTimeMillis() + "_" + originalFilename; //23456485_imgname.jpg
            String savePath = "C:/machimo_img/"+iImage; // 실제파일경로) C:/machimo_img/23456485_imgname.jpg
            boardFile.transferTo(new File(savePath)); //파일 저장
        }
    }

    @Override
    public void updateStatus(int ProductId, int PSalesStatus) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.updateStatus(ProductId, PSalesStatus);
    }

    @Override
    public void productDelete(int productId) {
        AdminDao dao = sqlSession.getMapper(AdminDao.class);
        dao.productDelete(productId);
    }

}

