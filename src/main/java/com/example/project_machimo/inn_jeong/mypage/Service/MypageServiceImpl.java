package com.example.project_machimo.inn_jeong.mypage.Service;

import com.example.project_machimo.inn_jeong.login.Dto.UsersDto;
import com.example.project_machimo.inn_jeong.mypage.Dao.MypageDao;
import com.example.project_machimo.inn_jeong.mypage.Dto.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service("MypageService")
public class MypageServiceImpl implements MypageService{

    @Autowired
    private SqlSession sqlSession;

    //구매내역 조회
    @Override
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<PurchaseItem> items = dao.getPurchaseItems(userId);

        return items;
    }

    //판매내역 조회
    @Override
    public ArrayList<SalesItem> getSalesItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<SalesItem> items = dao.getSalesItems(userId);
        return items;
    }

    //제품 삭제
    @Override
    public int deleteItem(Integer productId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.deleteItem(productId);
    }

    //유효성 검사
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    //유효성 검사 객체에서 hashmap으로 변환
    @Override
    public HashMap<String, String> switchRequestToUser(UserUpdateRequestDto requestDto) {
        HashMap<String,String> param = new HashMap<>();
        param.put("uId",requestDto.getUId());
        param.put("uPassword",requestDto.getUPassword());
        param.put("uName",requestDto.getUName());
        param.put("uJumin",requestDto.getUJumin());
        param.put("uNickname",requestDto.getUNickname());
        param.put("uPhone",requestDto.getUPhone());
        param.put("uEmail",requestDto.getUEmail());
        param.put("uAddrPostcode",requestDto.getUAddrPostcode());
        param.put("uAddress",requestDto.getUAddress());
        param.put("uAddressSub",requestDto.getUAddressSub());
        param.put("uSocial",requestDto.getUSocial());

        return param;
    }

    // 개인정보 수정
    @Override
    public int updateUser(HashMap<String, String> param) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.updateUser(param);
    }

    //id로 계정 조회
    @Override
    public UsersDto findUser(String uId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.findUser(uId);
    }

    //관심상품 조회
    @Override
    public ArrayList<WishItem> getWishItem(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.getWishItem(userId);
    }

    //입찰상품 조회
    @Override
    public ArrayList<AuctionItem> getAuctionItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<AuctionItem> items = dao.getAuctionItems(userId);
        System.out.println("@# getAuctuib userId =====>" +   userId);
        if(!items.isEmpty()){ //입찰상품 먼저 조회
            for (AuctionItem item:items) { //입찰상품의 현재 입찰가 조회하여 삽입
                AuctionItem currentItem = dao.getCurrentAmount(item.getProductsId());
                item.setUserBidsId(currentItem.getUserBidsId());
                item.setUBidsNickname(currentItem.getUBidsNickname());
                item.setCurrentAmount(currentItem.getCurrentAmount());
            }
        }
        return items;
    }

    //나의 문의내역 조회
    @Override
    public ArrayList<BoardItemDto> getBoards(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.getBoards(userId);
    }
}
