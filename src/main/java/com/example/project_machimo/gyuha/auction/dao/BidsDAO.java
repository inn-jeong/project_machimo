package com.example.project_machimo.gyuha.auction.dao;

import com.example.project_machimo.gyuha.auction.dto.BidsVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BidsDAO {
    List<BidsVO> bList(int id);

    Long hasBidHistory(Long id);

    Long maxAmount(int id);

    int write(Long amount,int id,Long firstPrice,Integer userID);

     void amountUpdate(Long amount,int id,Integer userId);
}