package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.dto.BidsVO;

import java.util.List;

public interface BidsService {
    List<BidsVO> bList(int id);
    boolean hasBidHistory(int id);

    Long maxAmount(int id);

    void write(Long amount, int id, Long firstPrice,Integer userId);


    void amountUpdate(Long amount, int id,Integer userId);



}
