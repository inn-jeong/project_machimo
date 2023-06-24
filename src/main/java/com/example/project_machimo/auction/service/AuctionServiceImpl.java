package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dto.AuctionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{

    private final AuctionDAO auctionDAO;

    @Autowired
    public AuctionServiceImpl(AuctionDAO auctionDAO) {
        this.auctionDAO = auctionDAO;
    }

    @Override
    public AuctionVO aList(int id) {
        AuctionVO auctionDTOS = auctionDAO.pList(id);
        return auctionDTOS;
    }

    @Override
    public void highestBidUpdate(Long amount, int id) {

        auctionDAO.highestBidUpdate(amount,id);
    }
}
