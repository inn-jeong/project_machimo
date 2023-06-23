package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.BidsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BidsDAO {
    List<BidsDTO> bList(int id);

    Long hasBidHistory(Long id);

    Long maxAmount(int id);

    int write(Long amount,int id,Long firstPrice);

     void amountUpdate(Long amount,int id);
}