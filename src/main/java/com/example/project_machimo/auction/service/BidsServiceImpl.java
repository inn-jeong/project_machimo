package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.BidsDAO;
import com.example.project_machimo.auction.dto.BidsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BidsServiceImpl implements BidsService {
    @Autowired
    private SqlSession session;

    @Override
    public List<BidsDTO> bList(int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        List<BidsDTO> bList = bidsDAO.bList(id);

        return bList;
    }

    @Override
    public boolean hasBidHistory(int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        

        return intIsNull( bidsDAO.hasBidHistory(id));

    }

    @Override
    public Integer maxAmount(int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        boolean isNull = intIsNull(bidsDAO.maxAmount(id));
        
        if(!isNull) return bidsDAO.maxAmount(id);
        else return 0;
    }

    @Override
    public void write(int amount, int id,int firstPrice) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        int write = bidsDAO.write(amount, id,firstPrice);

    }

    @Override
    public void amountUpdate(int amount, int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        bidsDAO.amountUpdate(amount,id);
    }


    private boolean intIsNull(Integer num){
        System.out.println("@#!$@!#@!#@!$@!# num : "+ num);
        if (num == null) return true;

        return false;
    }
}
