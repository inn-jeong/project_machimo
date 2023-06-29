package com.example.project_machimo.mypage.Service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("MypageService")
public class MypageServiceImpl implements MypageService{
    @Autowired
    private SqlSession sqlSession;
}
