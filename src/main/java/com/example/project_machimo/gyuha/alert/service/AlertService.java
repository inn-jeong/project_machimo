package com.example.project_machimo.gyuha.alert.service;

import com.example.project_machimo.gyuha.alert.dto.AlertVO;

import java.util.List;

public interface AlertService {
    void executeWinningBid();

    void failedAuctionNoBids();

    Integer alertResult(int uId);

    List<AlertVO> alList(int uId);

    void checkedFlag(int alId);

    int delete(int id);

    int deleteAll(int userId);
}
