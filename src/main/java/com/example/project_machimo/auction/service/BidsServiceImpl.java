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
        Long lId = (long) id;

        return intIsNull( bidsDAO.hasBidHistory(lId));

    }

    @Override
    public Long maxAmount(int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        boolean isNull = intIsNull(bidsDAO.maxAmount(id));
        if(!isNull) return bidsDAO.maxAmount(id);
        else return 0L;
    }

    @Override
    public void write(Long amount, int id,Long firstPrice) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        int write = bidsDAO.write(amount, id,firstPrice);

    }

    @Override
    public void amountUpdate(Long amount, int id) {
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        bidsDAO.amountUpdate(amount,id);
    }


    private boolean intIsNull(Long num){
        System.out.println("@#!$@!#@!#@!$@!# num : "+ num);
        if (num == null) return true;

        return false;
    }
}
