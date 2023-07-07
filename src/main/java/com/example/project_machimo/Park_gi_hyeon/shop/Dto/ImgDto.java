package com.example.project_machimo.Park_gi_hyeon.shop.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImgDto {
    private Integer iId;//이미지 번호
    private Integer productId;//제품 번호
    private String iImage;//이미지 파일
    private String iSubImg;//서브 이미지 파일
}
