package com.example.project_machimo.mypage.Dao;

import com.example.project_machimo.mypage.Dto.PurchaseItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MypageDao {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);

}
