package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.dao.AuctionDAO;
import com.example.project_machimo.gyuha.auction.dao.UserDAO;
import com.example.project_machimo.gyuha.auction.vo.AuctionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDAO auctionDAO;
    private final UserDAO userDAO;

    @Autowired
    public AuctionServiceImpl(AuctionDAO auctionDAO, UserDAO userDAO) {
        this.auctionDAO = auctionDAO;
        this.userDAO = userDAO;
    }


    @Override
    public AuctionVO aView(int id) {
        AuctionVO auctionVOS = auctionDAO.pView(id);
        return auctionVOS;
    }

    @Override
    public void highestBidUpdate(Long amount, int id, Integer userId) {

        auctionDAO.highestBidUpdate(amount, id);
    }

    @Override
    public int getUserId(Integer integer) {
        return userDAO.getUserId(integer);
    }

    @Override
    public boolean isSaleEnded(Timestamp period, int productStatus) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        switch (productStatus) {
            case 2, 3, 4, 5 -> {
                if (period.before(timestamp)) return true;
            }
        }
        return false;

    }
}
