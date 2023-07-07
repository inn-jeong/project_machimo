package com.example.project_machimo.jomuragi.productEnroll.dao;

import com.example.project_machimo.jomuragi.productEnroll.dto.CategoryVO;
import com.example.project_machimo.jomuragi.productEnroll.dto.ProductDto;
import com.example.project_machimo.jomuragi.productEnroll.dto.ProductImageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface EnrollMapper {

    ArrayList<CategoryVO> getCategories();

    ArrayList<CategoryVO> getSubCategories(int cId2);

    void write(ProductDto dto);


    /* 이미지 등록 */
    void imageEnroll(ProductImageVO vo);

    int getProductId();

    void deleteFile(int productId);


    /* 이미지 데이터 반환 */
    List<ProductImageVO> getAttachList(int productId);

}