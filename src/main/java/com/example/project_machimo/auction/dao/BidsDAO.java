package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.BidsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BidsDAO {
    List<BidsDTO> bList(int id);

    Integer hasBidHistory(int id);

    Integer maxAmount(int id);

    int write(int amount,int id,int firstPrice);

     void amountUpdate(int amount,int id);
}