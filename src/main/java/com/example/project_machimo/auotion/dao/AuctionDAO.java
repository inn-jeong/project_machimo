package com.example.project_machimo.auotion.dao;


import com.example.project_machimo.auotion.dto.AuctionDTO;
import com.example.project_machimo.auotion.dto.ProductsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionDAO {
    public List<AuctionDTO> pList(int pId);


}
