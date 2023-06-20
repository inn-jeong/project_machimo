package com.example.project_machimo.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private Integer products_id;
    private Integer users_id;
    private byte category_id;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Integer hit;
    private String products_info;
    private Integer price_i;
    private Timestamp auction_dur;
    private Integer price_a;
    private Integer sales_status;
    private Integer sale_type;
    private String p_account;
    private String p_address;
}


