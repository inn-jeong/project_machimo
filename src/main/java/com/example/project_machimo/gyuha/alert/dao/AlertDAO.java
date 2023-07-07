package com.example.project_machimo.gyuha.alert.dao;

import com.example.project_machimo.gyuha.alert.dto.AlertVO;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface AlertDAO {
    void executeWinningBid();
    void failedAuctionNoBids();

    Integer alertResult(int uId);

    List<AlertVO> alList(int alId);

    void checkedFlag(int alId);
    List<AlertVO> alAlList();


}
