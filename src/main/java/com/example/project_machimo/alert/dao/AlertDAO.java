package com.example.project_machimo.alert.dao;

import com.example.project_machimo.alert.dto.AlretVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AlertDAO {
    void executeWinningBid();
    void failedAuctionNoBids();

    Integer countResult(int uId);

    List<AlretVO> alList(int uId);

    void checkedFlag(int alId);



}
