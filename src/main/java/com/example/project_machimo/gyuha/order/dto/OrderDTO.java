package com.example.project_machimo.gyuha.order.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
/*-최규하
db와 service에서 주고 받을 DTO 객체*/
@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {

    private Integer orderId;
    private Integer orderPrice;
    private List<Integer> productIdList;
    private String orderReq;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String orderAddress;
    private String orderAddressSub;
    private Integer userId;
    private Integer usedPointResult;



}
