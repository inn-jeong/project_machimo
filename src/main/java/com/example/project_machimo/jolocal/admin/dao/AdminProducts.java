package com.example.project_machimo.jolocal.admin.dao;

import com.example.project_machimo.jolocal.admin.dto.StatusDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminProducts {
    int directSalesStatus(StatusDto dto);
    int auctionStatus(StatusDto dto);
}
