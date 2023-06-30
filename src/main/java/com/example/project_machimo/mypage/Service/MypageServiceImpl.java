package com.example.project_machimo.mypage.Service;

import com.example.project_machimo.mypage.Dao.MypageDao;
import com.example.project_machimo.mypage.Dto.PurchaseItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("MypageService")
public class MypageServiceImpl implements MypageService{

    @Autowired
    private SqlSession sqlSession;

    @Override
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<PurchaseItem> items = dao.getPurchaseItems(userId);

        return items;
    }
}
