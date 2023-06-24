package com.example.project_machimo.search.service;

import com.example.project_machimo.search.dao.SearchDAO;
import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.SearchVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class SearchServiceImpl implements SearchService {

    private  final SearchDAO searchDAO;

    @Autowired
    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    @Override
    public List<SearchVO> search(byte searchOption, String keyword, Criteria cri) {

        switch (searchOption){
            case 1 -> {
                return searchDAO.getListWithPaging(keyword, cri);
            }
            case 2 ->{
                return searchDAO.searchProductName(keyword);
            }
            case 3 ->{
                return searchDAO.searchProductInfo(keyword);
            }
            case 4 ->{
                return searchDAO.searchProductNameOrInfo(keyword);
            }
        }
        return Collections.emptyList();

    }

    @Override
    public int searchTotal(String keyword) {

        return searchDAO.searchUsersTotal(keyword);
    }
}
