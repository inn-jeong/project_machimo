package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.dao.AuctionDAO;
import com.example.project_machimo.gyuha.auction.dao.UserDAO;
import com.example.project_machimo.gyuha.auction.dto.AuctionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionServiceImpl implements AuctionService{

    private final AuctionDAO auctionDAO;
    private final UserDAO userDAO;

    @Autowired
    public AuctionServiceImpl(AuctionDAO auctionDAO, UserDAO userDAO) {
        this.auctionDAO = auctionDAO;
        this.userDAO = userDAO;
    }



    @Override
    public AuctionVO aList(int id) {
        AuctionVO auctionVOS = auctionDAO.pList(id);
        return auctionVOS;
    }

    @Override
    public void highestBidUpdate(Long amount, int id,Integer userId) {

        auctionDAO.highestBidUpdate(amount,id);
    }

    @Override
    public int getUserId(Integer integer) {
        return userDAO.getUserId(integer);
    }
}
