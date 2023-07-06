package com.example.project_machimo.order.service;

import com.example.project_machimo.auction.dao.ProductsDAO;
import com.example.project_machimo.order.dao.OrderDAO;
import com.example.project_machimo.order.dto.BuyProductVO;
import com.example.project_machimo.order.dto.BuyerVO;
import com.example.project_machimo.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    private final ProductsDAO productsDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, ProductsDAO productsDAO) {
        this.orderDAO = orderDAO;
        this.productsDAO = productsDAO;
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
    public int response(OrderDTO orderDTO) {


        return  getResult(orderDTO);
    }

    @Override
    public void completed(OrderDTO orderDTO, Integer productIds) {
        orderDAO.insertOrderProducts(orderDTO.getOrderId(), productIds);
        productsDAO.updateProductStatusCompletedCase(productIds);
    }


    private int getResult(OrderDTO orderDTO) {
        int orderStatus = orderDAO.insertOrderStatus();
        int insertOrder = orderDAO.insertOrder(orderDTO);
        int updateUserPoint = orderDAO.updateUserPoint(orderDTO.getUserId(), orderDTO.getUsedPointResult());

        int result = orderStatus + insertOrder+updateUserPoint;
        return result;
    }

    @Override
    public int getOrderProductTotal(int orderId) {
        return orderDAO.getOrderProductTotal(orderId);
    }

    @Override
    public int getAmountResult(List<Integer> productIds) {
        int result = 0;
        for (Integer productId : productIds) {
            result += orderDAO.getAmountResult(productId);
        }
        return result;
    }

    @Override
    public int getUserPoint(int userId) {

        return orderDAO.getUserPoint(userId);
    }
}


