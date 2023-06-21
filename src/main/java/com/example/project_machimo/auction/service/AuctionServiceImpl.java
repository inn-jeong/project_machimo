package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dto.AuctionDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService{
    @Autowired
    private SqlSession session;
    @Override
    public List<AuctionDTO> aList(int id) {
        AuctionDAO auctionDAO = session.getMapper(AuctionDAO.class);
        List<AuctionDTO> auctionDTOS = auctionDAO.pList(id);
        return auctionDTOS;
    }

    @Override
    public void highestBidUpdate(int amount, int id) {
        AuctionDAO auctionDAO = session.getMapper(AuctionDAO.class);
        auctionDAO.highestBidUpdate(amount,id);
    }
}
