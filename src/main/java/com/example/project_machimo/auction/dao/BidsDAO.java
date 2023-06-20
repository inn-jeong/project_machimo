package com.example.project_machimo.auction.dao;

import com.example.project_machimo.auction.dto.BidsDTO;

import java.util.List;

public interface BidsDAO {
    List<BidsDTO> bList(int id);
}
