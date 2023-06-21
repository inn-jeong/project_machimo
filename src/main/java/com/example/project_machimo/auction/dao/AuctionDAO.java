package com.example.project_machimo.auction.dao;


import com.example.project_machimo.auction.dto.AuctionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface AuctionDAO {
    public List<AuctionDTO> pList(int id);
    public void highestBidUpdate(int amount,int id);

    public List<AuctionDTO> endList(Timestamp timestamp);
}
