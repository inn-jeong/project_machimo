package com.example.project_machimo.search.service;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SearchService {
    List<SearchVO> search(byte searchOption, String keyword, Criteria cri);

    int searchTotal(String keyword);
}
