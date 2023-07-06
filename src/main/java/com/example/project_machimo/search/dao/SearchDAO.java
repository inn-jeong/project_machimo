package com.example.project_machimo.search.dao;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SearchDAO {
    List<SearchVO>searchListPage(Criteria cri);
    int searchUsersTotal(Criteria cri);


}
