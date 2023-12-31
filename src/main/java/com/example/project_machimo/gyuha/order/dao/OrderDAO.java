package com.example.project_machimo.gyuha.order.dao;

import com.example.project_machimo.gyuha.order.vo.BuyProductVO;
import com.example.project_machimo.gyuha.order.vo.BuyerVO;
import com.example.project_machimo.gyuha.order.dto.OrderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/*
-최규하
주문 테이블과 관련된 것들을 처리함
*/

@Mapper
public interface OrderDAO {
    BuyProductVO getProduct(Integer sellerId);
    BuyerVO getUser(Integer userId);

    Integer getOrderId();

    Integer getTotalAmount(Integer productId);

    int insertOrder(OrderDTO orderDTO);

    int insertOrderProducts(
             @Param("orderId")int orderId
            ,@Param("productId") int productId
            ,@Param("userId")int userId);
    int getOrderProductTotal(@Param("orderId")int orderId);

    int updateUserPoint(@Param("userId")int userId,@Param("point")int point);

    int getUserPoint(int userId);
    int getAmountResult(int productId);

    int deleteOrder(int orderId);

    int deleteOrderProducts(int orderId);

    int updateSalesStatus(int productId);



}
