package com.example.project_machimo.gyuha.search.service;

import com.example.project_machimo.gyuha.search.dto.SearchCriteria;
import com.example.project_machimo.gyuha.search.dto.SearchVO;

import java.util.List;

public interface SearchService {
    List<SearchVO> search(SearchCriteria cri);

    int searchTotal(SearchCriteria cri);
}
