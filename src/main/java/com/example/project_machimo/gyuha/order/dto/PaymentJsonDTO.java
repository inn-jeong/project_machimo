package com.example.project_machimo.gyuha.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 - 최규하
아임포트에 값을 받기위한 DTO
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentJsonDTO {

    private String impUid;
    private Integer merchantUid;
    private String amount;
    private List<Integer> productList;




}
