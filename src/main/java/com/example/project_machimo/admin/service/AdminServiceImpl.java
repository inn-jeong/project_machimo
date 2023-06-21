package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.AdminDao;
import com.example.project_machimo.admin.dto.Criteria;
import com.example.project_machimo.admin.dto.UsersDto;
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
}
