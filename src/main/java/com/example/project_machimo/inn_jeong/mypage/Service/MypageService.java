package com.example.project_machimo.inn_jeong.mypage.Service;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.inn_jeong.mypage.Dto.*;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MypageService {
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId);
    public ArrayList<SalesItem> getSalesItems(Integer userId);
    public int deleteItem(Integer productId);
    public Map<String, String> validateHandling(Errors errors);
    public HashMap<String,String> switchRequestToUser(UserUpdateRequestDto requestDto);
    public int updateUser(HashMap<String, String> param);
    public UsersDto findUser(String uId);
    public ArrayList<WishItem> getWishItem(Integer userId);
    public ArrayList<AuctionItem> getAuctionItems(Integer userId);
    public ArrayList<BoardItemDto> getBoards(Integer userId);
}
