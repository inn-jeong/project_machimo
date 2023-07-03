package com.example.project_machimo.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BuyProductVO(
         Integer productId
        ,String pName

        , Integer userId
        ,Integer pDirect

) {

}
