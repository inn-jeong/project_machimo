package com.example.project_machimo.auction.service;

public interface BidsService {
    boolean hasBidHistory(int id);
    Integer maxAmount(int id);



}
