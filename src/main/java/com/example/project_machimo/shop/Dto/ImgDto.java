package com.example.project_machimo.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgDto {
    private Integer i_id;//이미지 번호
    private Integer product_id;//제품 번호
    private String i_image;//이미지 파일
    private String i_sub_image;//서브 이미지 파일
}
