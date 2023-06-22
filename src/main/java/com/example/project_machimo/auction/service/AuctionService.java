package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.AuctionDTO;

import java.util.List;

public interface AuctionService {
    public AuctionDTO aList(int id);
    void highestBidUpdate(int amount, int id);
}
