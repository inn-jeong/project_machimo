package com.example.project_machimo.gyuha.auction.dao;


import com.example.project_machimo.gyuha.auction.vo.AuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AuctionDAO {
    public AuctionVO pView(int id);
    public void highestBidUpdate(Long amount,int id);

    public List<AuctionVO> endList(Timestamp timestamp);


    void updateHit(Integer productId);
}
