package com.example.project_machimo.basket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private Integer basketId;
    private Integer userId;
    private Integer productId;
}