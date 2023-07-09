package com.example.project_machimo.gyuha.search.controller;

import com.example.project_machimo.gyuha.search.dto.SearchCriteria;
import com.example.project_machimo.gyuha.search.dto.SearchPageDTO;
import com.example.project_machimo.gyuha.search.service.SearchService;
import com.example.project_machimo.gyuha.search.vo.SearchVO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/*
-최규하
검색결과 처리 및 페이징
* */
@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }



/*    
    검색결과 및  페이징
    검색 결과가 있으면 아래와 같이 기본적인 페이징및 검색결과 리스트를 model에 담에서
    search/searchPage에 반환함
    검색 결과가 없다면 search/errorPage 아래와 같은 페이지를 반환함 
        */

    @RequestMapping("/searchList")
    public String searchPage(
            Model model
            , @ModelAttribute SearchCriteria cri
            , HttpSession session
    ) {

        List<SearchVO> search = searchService.search(cri);

        log.info("cri.getPageNum() =====> {}", cri.getPageNum());
        log.info("cri.getAmount() =====> {}", cri.getAmount());

        int total = searchService.searchTotal(cri);
        session.setAttribute("keyword", cri.getKeyword());
        session.setAttribute("searchOption", cri.getSearchOption());
        log.info("토탈 === > {}", total);
        String keyword = cri.getKeyword();
        if (search.isEmpty()) {
            keyword = keyword + "에 대한 검색결과가 없습니다.";
            model.addAttribute("message", keyword);
            return "search/errorPage";
        } else {


            model.addAttribute("message", keyword);
            model.addAttribute("search", search);
            model.addAttribute("option", cri.getSearchOption());
            SearchPageDTO pageDTO = new SearchPageDTO(total, cri);
            log.info("isNext === >{}", pageDTO.isNext());
            log.info("isPrev === >{}", pageDTO.isPrev());
            model.addAttribute("pageMaker", pageDTO);

            return "search/searchPage";
        }

    }

}
