package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.BidsDTO;

import java.util.List;

public interface BidsService {
    List<BidsDTO> bList(int id);
    boolean hasBidHistory(int id);

    Long maxAmount(int id);

    void write(Long amount, int id, Long firstPrice);


    void amountUpdate(Long amount, int id);



}
