package com.example.project_machimo.auction.handler;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dao.BidsDAO;
import com.example.project_machimo.auction.dao.ProductsDAO;
import com.example.project_machimo.auction.dto.AuctionDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.SQLRecoverableException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionExpirationHandler {
    @Autowired
    private SqlSession session;

    @Scheduled(fixedDelay = 10000)
    public void executeTask() {
        System.out.println("동작하니??");

        ProductsDAO productsDAO = session.getMapper(ProductsDAO.class);
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);

        LocalDateTime now = LocalDateTime.now();
        AuctionDAO auctionDAO = session.getMapper(AuctionDAO.class);
        List<AuctionDTO> auctionDTOS = auctionDAO.endList(Timestamp.valueOf(now));









    }
}
