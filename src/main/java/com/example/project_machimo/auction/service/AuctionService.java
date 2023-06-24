package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.AuctionVO;

public interface AuctionService {
    public AuctionVO aList(int id);
    void highestBidUpdate(Long amount, int id);
}
