package com.example.project_machimo.mypage.Service;

import com.example.project_machimo.login.Dto.UserRequestDto;
import com.example.project_machimo.login.Dto.UsersDto;
import com.example.project_machimo.mypage.Dao.MypageDao;
import com.example.project_machimo.mypage.Dto.*;
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

    @Override
    public ArrayList<PurchaseItem> getPurchaseItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<PurchaseItem> items = dao.getPurchaseItems(userId);

        return items;
    }

    @Override
    public ArrayList<SalesItem> getSalesItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        ArrayList<SalesItem> items = dao.getSalesItems(userId);
        return items;
    }

    @Override
    public int deleteItem(Integer productId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.deleteItem(productId);
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

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

    @Override
    public int updateUser(HashMap<String, String> param) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.updateUser(param);
    }

    @Override
    public UsersDto findUser(String uId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.findUser(uId);
    }

    @Override
    public ArrayList<WishItem> getWishItem(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.getWishItem(userId);
    }

    @Override
    public ArrayList<AuctionItem> getAuctionItems(Integer userId) {
        MypageDao dao = sqlSession.getMapper(MypageDao.class);
        return dao.getAuctionItems(userId);
    }
}
