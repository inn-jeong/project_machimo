package com.example.project_machimo.gyuha.search.service;

import com.example.project_machimo.gyuha.search.dto.Criteria;
import com.example.project_machimo.gyuha.search.dto.SearchVO;

import java.util.List;

public interface SearchService {
    List<SearchVO> search(Criteria cri);

    int searchTotal(Criteria cri);
}
