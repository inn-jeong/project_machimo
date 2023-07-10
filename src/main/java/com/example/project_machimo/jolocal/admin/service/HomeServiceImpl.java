package com.example.project_machimo.jolocal.admin.service;

import com.example.project_machimo.jolocal.admin.dao.HomeDao;
import com.example.project_machimo.jolocal.admin.dto.BoardDto;
import com.example.project_machimo.jolocal.admin.dto.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Autowired
    private SqlSession sqlSession;
    @Override
    public ArrayList<BoardDto> boardList(Criteria cri) {
        HomeDao dao = sqlSession.getMapper(HomeDao.class);
        return dao.boardList(cri);
    }

    @Override
    public int getTotalCount() {
        HomeDao dao = sqlSession.getMapper(HomeDao.class);
        return dao.getTotalCount();
    }

    @Override
    public ArrayList<BoardDto> userQnAList(Criteria cri) {
        HomeDao dao = sqlSession.getMapper(HomeDao.class);
        return dao.userQnAList(cri);
    }

    @Override
    public ArrayList<BoardDto> userBoardList(Criteria cri) {
        HomeDao dao = sqlSession.getMapper(HomeDao.class);
        return dao.userBoardList(cri);
    }
}
