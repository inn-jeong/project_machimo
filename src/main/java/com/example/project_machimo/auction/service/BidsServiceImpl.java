package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.BidsDAO;
import com.example.project_machimo.auction.dto.BidsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidsServiceImpl implements BidsService {


    private final BidsDAO bidsDAO;

    @Autowired
    public BidsServiceImpl(BidsDAO bidsDAO) {
        this.bidsDAO = bidsDAO;
    }


    @Override
    public List<BidsVO> bList(int id) {

        List<BidsVO> bList = bidsDAO.bList(id);

        return bList;
    }

    @Override
    public boolean hasBidHistory(int id) {
        Long lId = (long) id;
        return intIsNull( bidsDAO.hasBidHistory(lId));

    }

    @Override
    public Long maxAmount(int id) {

        boolean isNull = intIsNull(bidsDAO.maxAmount(id));
        if(!isNull) return bidsDAO.maxAmount(id);
        else return 0L;
    }

    @Override
    public void write(Long amount, int id,Long firstPrice,Integer userId) {

        int write = bidsDAO.write(amount, id,firstPrice, userId);

    }

    @Override
    public void amountUpdate(Long amount, int id,Integer userId) {

        bidsDAO.amountUpdate(amount,id,userId);
    }


    private boolean intIsNull(Long num){
        System.out.println("@#!$@!#@!#@!$@!# num : "+ num);
        if (num == null) return true;
        return false;
    }
}
