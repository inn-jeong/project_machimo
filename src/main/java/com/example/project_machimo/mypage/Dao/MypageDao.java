package com.example.project_machimo.mypage.Dao;

import com.example.project_machimo.mypage.Dto.PurchaseItem;
import com.example.project_machimo.mypage.Dto.SalesItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface MypageDao {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
}
