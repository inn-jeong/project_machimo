package com.example.project_machimo.gyuha.alert.dao;

import com.example.project_machimo.gyuha.alert.vo.AlertVO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlertDAO {
    void executeWinningBid();
    void failedAuctionNoBids();

    Integer alertResult(int uId);

    List<AlertVO> alList(int alId);

    void checkedFlag(int alId);
    List<AlertVO> alAlList();

    int delete(int id);

    int deleteAll(int userId);



}
