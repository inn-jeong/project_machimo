package com.example.project_machimo.gyuha.order.service;

import com.example.project_machimo.gyuha.auction.dao.ProductsDAO;
import com.example.project_machimo.gyuha.order.dao.OrderDAO;
import com.example.project_machimo.gyuha.order.dto.BuyProductVO;
import com.example.project_machimo.gyuha.order.dto.BuyerVO;
import com.example.project_machimo.gyuha.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
            log.info("productInfo === > {}", product.productId());
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


        return getResult(orderDTO);
    }

    @Override
    public void completed(OrderDTO orderDTO, Integer productIds) {
        orderDAO.insertOrderProducts(orderDTO.getOrderId(), productIds,orderDTO.getUserId() );
        productsDAO.updateProductStatusCompletedCase(productIds);
    }


    private int getResult(OrderDTO orderDTO) {

        int insertOrder = orderDAO.insertOrder(orderDTO);
        int updateUserPoint = orderDAO.updateUserPoint(orderDTO.getUserId(), orderDTO.getUsedPointResult());

        int result = insertOrder + updateUserPoint;
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

    @Override
    public int cancelDb(OrderDTO orderDTO) {
        Integer orderId = orderDTO.getOrderId();
        int deleteOrderProducts = orderDAO.deleteOrderProducts(orderId);
        int deleteOrder = orderDAO.deleteOrder(orderId);
        Integer userId = orderDTO.getUserId();
        Integer usedPointResult = orderDTO.getUsedPointResult();

        List<Integer> productIdList = orderDTO.getProductIdList();
        int updateSalesStatus = 0 ;
        for (Integer integer : productIdList) {
            updateSalesStatus += orderDAO.updateSalesStatus(integer);
        }

        int updateUserPoint = orderDAO.updateUserPoint(userId, usedPointResult);

        int result = usedPointResult+deleteOrderProducts+deleteOrder+updateSalesStatus;


        return result;
    }


}