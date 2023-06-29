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
    public List<SearchVO> search(Criteria cri) {



        if (searchDAO.searchListPage(cri) != null)
            return searchDAO.searchListPage( cri);


        return Collections.emptyList();

    }

    @Override
    public int searchTotal(Criteria cri) {

        return searchDAO.searchUsersTotal(cri);
    }
}
