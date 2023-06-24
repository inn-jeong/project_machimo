package com.example.project_machimo.auction.config;

import com.example.project_machimo.auction.dao.AuctionDAO;
import com.example.project_machimo.auction.dao.BidsDAO;
import com.example.project_machimo.auction.dao.ProductsDAO;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class MyBatisConfig {


//
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
//
//    @Bean
//    public ProductsDAO productsDAO() {
//        return sqlSessionFactory.openSession().getMapper(ProductsDAO.class);
//    }
//    @Bean
//    public BidsDAO bidsDAO() {
//        return sqlSessionFactory.openSession().getMapper(BidsDAO.class);
//
//    }
//    @Bean
//    public AuctionDAO auctionDAO(){
//        return sqlSessionFactory.
//    }

}

