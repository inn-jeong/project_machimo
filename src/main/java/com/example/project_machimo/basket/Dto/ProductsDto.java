package com.example.project_machimo.basket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {
    private Integer product_id;
    private Integer c_id;
    private Integer c_id2;
    private Integer user_id;
    private String p_name;
    private String p_info;
    private Integer p_direct;
    private Timestamp p_dur;
    private Integer p_b_price;
    private Timestamp p_created_at;
    private Timestamp p_updated_at;
    private Integer p_hit;
    private Integer p_sales_status;
    private Integer p_sale_type;
    private String p_account;
    private String p_address;
    private String p_bank;
}
