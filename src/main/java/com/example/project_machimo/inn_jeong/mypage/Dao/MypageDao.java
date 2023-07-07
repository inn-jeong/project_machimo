package com.example.project_machimo.inn_jeong.mypage.Dao;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.inn_jeong.mypage.Dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface MypageDao {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
    public int updateUser(HashMap<String, String> param);
    public UsersDto findUser(String uId);
    public ArrayList<WishItem> getWishItem(Integer userId);
    public ArrayList<AuctionItem> getAuctionItems(Integer userId);
    public AuctionItem getCurrentAmount(Integer productId);
    public ArrayList<BoardDto> getBoards(Integer userId);
}
