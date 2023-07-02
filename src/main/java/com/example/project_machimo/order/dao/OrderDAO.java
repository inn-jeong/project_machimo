package com.example.project_machimo.order.dao;

import com.example.project_machimo.order.dto.BuyProductVO;
import com.example.project_machimo.order.dto.BuyerVO;
import com.example.project_machimo.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDAO {
    BuyProductVO getProduct(Integer sellerId);
    BuyerVO getUser(Integer userId);

    Integer getOrderId();

    Integer getTotalAmount(Integer productId);

    int insertOrder(OrderDTO orderDTO);

    int insertOrderStatus();
    int insertOrderProducts(@Param("orderId")int orderId, @Param("productId") int productId);
    int getOrderProductTotal(@Param("orderId")int orderId);



}
