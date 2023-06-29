package com.example.project_machimo.search.service;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.SearchVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchService {
    List<SearchVO> search(Criteria cri);

    int searchTotal(Criteria cri);
}
