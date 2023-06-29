package com.example.project_machimo.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {
    private Integer order_id;
    private Integer product_id;
    private Integer user_id;
    private String u_name;
    private String p_name;
    private Integer p_direct;
    private String i_sub_img;
    private String created_at;
    private String order_status;
}