package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.vo.AuctionVO;

import java.sql.Timestamp;

public interface AuctionService {
    public AuctionVO aList(int id);

    void highestBidUpdate(Long amount, int id, Integer userId);

    int getUserId(Integer integer);

    boolean isSaleEnded(Timestamp period, int productStatus);
}