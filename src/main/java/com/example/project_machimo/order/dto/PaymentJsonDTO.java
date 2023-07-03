package com.example.project_machimo.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentJsonDTO {

    private String impUid;
    private Integer merchantUid;
    private String amount;
    private List<Integer> productList;




}
