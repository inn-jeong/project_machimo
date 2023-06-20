package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.UsersDao;
import com.example.project_machimo.admin.dto.UsersDto;
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
    public ArrayList<UsersDto> list() {
        log.info("@# AdminServiceImpl.list() start");
        UsersDao dao = sqlSession.getMapper(UsersDao.class);
        ArrayList<UsersDto> list = dao.list();
        log.info("@# AdminServiceImpl.list() end");
        return list;
    }


}
