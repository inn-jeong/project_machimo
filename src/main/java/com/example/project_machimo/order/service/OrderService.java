package com.example.project_machimo.order.service;

import com.example.project_machimo.order.dto.BuyProductVO;
import com.example.project_machimo.order.dto.BuyerVO;
import com.example.project_machimo.order.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public interface OrderService {
    List<BuyProductVO> getBuyList(List<Integer> sellerIds);

    BuyerVO getUser(Integer integer);

    Integer getOrderId();

    ResponseEntity<? extends Objects> response (OrderDTO orderDTO);

    int getOrderProductTotal (int orderId);
}
