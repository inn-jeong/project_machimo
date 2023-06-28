package com.example.project_machimo.basket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemDto {
    private Integer product_id;
    private Integer user_id;
    private String u_name;
    private String p_name;
    private Integer p_direct;
    private Integer p_sales_status;
    private String i_sub_img;
}
