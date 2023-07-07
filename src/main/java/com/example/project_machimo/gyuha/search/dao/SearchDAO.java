package com.example.project_machimo.gyuha.search.dao;

import com.example.project_machimo.gyuha.search.dto.Criteria;
import com.example.project_machimo.gyuha.search.dto.SearchVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SearchDAO {

// 페이징과 결과값 출력을 위한 dao 메소드
    List<SearchVO>searchListPage(Criteria cri);
//    검색 결과의 갯수를 반환하는 dao 메소드
    int searchUsersTotal(Criteria cri);


}
