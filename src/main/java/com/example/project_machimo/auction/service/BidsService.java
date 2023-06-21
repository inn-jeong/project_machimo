package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dto.BidsDTO;

import java.util.List;

public interface BidsService {
    List<BidsDTO> bList(int id);
    boolean hasBidHistory(int id);

    Integer maxAmount(int id);

    void write(int amount, int id, int firstPrice);


    void amountUpdate(int amount, int id);



}
