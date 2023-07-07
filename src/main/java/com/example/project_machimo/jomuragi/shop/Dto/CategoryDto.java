package com.example.project_machimo.jomuragi.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String cName;//카테고리 이름
    private Integer cId;//카테고리 번호
    private Integer cId2;//카테고리 번호자기참조
}
