package com.example.project_machimo.gyuha.auction.handler;

import com.example.project_machimo.gyuha.alert.dao.AlertDAO;
import com.example.project_machimo.gyuha.auction.dao.AuctionDAO;
import com.example.project_machimo.gyuha.auction.dao.BidsDAO;
import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.auction.dto.AuctionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionExpirationHandler {

    final private SqlSession session;

    @Autowired
    public AuctionExpirationHandler(SqlSession session) {
        this.session = session;
    }

//    @Scheduled(fixedDelay = 10000)
    @Scheduled(cron = "0 0 * * * *")
    public void executeTask() {
        System.out.println("동작하니??");

        ProductsDAO productsDAO = session.getMapper(ProductsDAO.class);
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        AlertDAO alertDAO = session.getMapper(AlertDAO.class);
        LocalDateTime now = LocalDateTime.now();
        AuctionDAO auctionDAO = session.getMapper(AuctionDAO.class);
        List<AuctionVO> auctionVOS = auctionDAO.endList(Timestamp.valueOf(now));

        for (AuctionVO auctionVO : auctionVOS) {
            if(auctionVO.highestBid()==null){
                productsDAO.failedSale(Timestamp.valueOf(now), auctionVO.productsId());


            }else {
                productsDAO.succeedsSale(Timestamp.valueOf(now), auctionVO.productsId());
            }

        }

                productsDAO.updatePrice();
                alertDAO.failedAuctionNoBids();
                alertDAO.executeWinningBid();
    }

}
