package com.example.project_machimo.gyuha.auction.dao;


import com.example.project_machimo.gyuha.auction.dto.AuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AuctionDAO {
    public AuctionVO pList(int id);
    public void highestBidUpdate(Long amount,int id);

    public List<AuctionVO> endList(Timestamp timestamp);

}