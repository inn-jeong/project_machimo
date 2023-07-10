package com.example.project_machimo.gyuha.order.service;

import com.example.project_machimo.gyuha.order.vo.BuyProductVO;
import com.example.project_machimo.gyuha.order.vo.BuyerVO;
import com.example.project_machimo.gyuha.order.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<BuyProductVO> getBuyList(List<Integer> sellerIds);

    BuyerVO getUser(Integer integer);

    Integer getOrderId();

    int response (OrderDTO orderDTO);

    int getOrderProductTotal (int orderId);

    int getAmountResult ( List<Integer> productIds);
    int getUserPoint(int userId);
    void completed(OrderDTO orderDTO, Integer productIds);

    int cancelDb(OrderDTO orderDTO);
}
