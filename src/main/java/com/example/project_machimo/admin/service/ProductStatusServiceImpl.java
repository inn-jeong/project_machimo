package com.example.project_machimo.admin.service;

import com.example.project_machimo.admin.dao.AdminProducts;
import com.example.project_machimo.admin.dto.StatusDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {

    private final AdminProducts adminProducts;

    @Autowired
    public ProductStatusServiceImpl(AdminProducts adminProducts) {
        this.adminProducts = adminProducts;
    }

    @Override
    public int update(StatusDto dto) {
        System.out.println("@# productStatusServiceImpl");
        System.out.println("@# pSaleType ==> "+dto.pSaleType());
        int i = 0; //변수초기화
//        경매 0 즉시판매 1
        Integer pSaleType = dto.pSaleType();
        if (pSaleType == 0){
             i = adminProducts.auctionStatus(dto);
        }else {
            i = adminProducts.directSalesStatus(dto);
        }
        return i;
    }
}
