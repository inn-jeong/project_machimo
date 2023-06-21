package com.example.project_machimo.auction.service;

import com.example.project_machimo.auction.dao.BidsDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BidsServiceImpl implements BidsService {
    @Autowired
    private SqlSession session;
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

    public int write(Map<String,Integer> param){
        BidsDAO bidsDAO = session.getMapper(BidsDAO.class);
        Integer productId = bidsDAO.maxAmount(param.get("productId"));
        boolean isNull = intIsNull(productId);


        if(!isNull){

        }else{

        }



    }



    private boolean intIsNull(Integer num){
        if (num == null) return true;

        return false;
    }
}
