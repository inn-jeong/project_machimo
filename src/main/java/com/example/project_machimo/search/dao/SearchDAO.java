package com.example.project_machimo.search.dao;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SearchDAO {
    List<SearchVO> searchUser(String keyword);
    List<SearchVO> searchProductName(String keyword);
    List<SearchVO> searchProductInfo(String keyword);
    List<SearchVO> searchProductNameOrInfo(String keyword);

    List<SearchVO>getListWithPaging(@Param("keyword")String keyword, @Param("cri") Criteria cri);
    int searchUsersTotal(String keyword);

}
