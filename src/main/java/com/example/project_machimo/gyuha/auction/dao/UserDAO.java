package com.example.project_machimo.gyuha.auction.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {

    int getUserId (Integer userId);
}
