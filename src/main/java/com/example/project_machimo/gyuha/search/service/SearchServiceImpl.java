package com.example.project_machimo.gyuha.search.service;

import com.example.project_machimo.gyuha.search.dao.SearchDAO;
import com.example.project_machimo.gyuha.search.dto.SearchCriteria;
import com.example.project_machimo.gyuha.search.dto.SearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
/* -최규하
 dao에서 받은 서치 결과를 처리해서 내려주는 service*/
@Service
public class SearchServiceImpl implements SearchService {

    private  final SearchDAO searchDAO;

    @Autowired
    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }


    /*
    -최규하

    받아온 dao에서 받아온 서치결과를 리턴해줌
    서치 결과가 있다면 dao에서 받은 결과를 그대로 리턴해주고
    없다면 isEmpty를 리턴해줌*/
    @Override
    public List<SearchVO> search(SearchCriteria cri) {



        if (searchDAO.searchListPage(cri) != null)
            return searchDAO.searchListPage( cri);


        return Collections.emptyList();

    }
//  검색결과 갯수의 토탈를 리턴해줌
    @Override
    public int searchTotal(SearchCriteria cri) {

        return searchDAO.searchUsersTotal(cri);
    }
}
