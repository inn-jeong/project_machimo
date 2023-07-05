package com.example.project_machimo.basket.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class BasketDTO {
    private Integer productId;
    private Integer userId;




}
