package com.example.project_machimo.order.service;

import com.example.project_machimo.order.dao.OrderDAO;
import com.example.project_machimo.order.dto.BuyProductVO;
import com.example.project_machimo.order.dto.BuyerVO;
import com.example.project_machimo.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<BuyProductVO> getBuyList(List<Integer> sellerIds) {
        List<BuyProductVO> list = new ArrayList<>();
        for (Integer sellerId : sellerIds) {
            BuyProductVO product = orderDAO.getProduct(sellerId);
            list.add(product);
        }

        return list;
    }

    @Override
    public BuyerVO getUser(Integer userId) {
        return orderDAO.getUser(userId);
    }

    @Override
    public Integer getOrderId() {
        return orderDAO.getOrderId();
    }

    @Override
    public ResponseEntity<? extends Objects> response(OrderDTO orderDTO) {

        int orderStatus = orderDAO.insertOrderStatus();
        int insertOrder = orderDAO.insertOrder(orderDTO);
        int result = orderStatus + insertOrder;

        List<Integer> productIdList = orderDTO.getProductIdList();

        if (result == 2) {
            for (Integer productIds : productIdList) {
                orderDAO.insertOrderProducts(orderDTO.getOrderId(), productIds);
            }
            return ResponseEntity.ok().build();

        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public int getOrderProductTotal(int orderId) {
        return orderDAO.getOrderProductTotal(orderId);
    }
}


