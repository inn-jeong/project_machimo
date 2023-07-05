package com.example.project_machimo.search.controller;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.PageDTO;
import com.example.project_machimo.search.dto.SearchVO;
import com.example.project_machimo.search.service.SearchService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/list")
    public String search(
            Model model
            , @ModelAttribute Criteria cri
            , HttpSession session
    ) {


        log.info("키워드에용에용" + cri.getKeyword());
        log.info("서치옵션이에용" + cri.getSearchOption());
        log.info("페이지넘버에용" + String.valueOf(cri.getPageNum()));
        log.info("어먼트에용에용" + String.valueOf(cri.getAmount()));

        List<SearchVO> search = searchService.search(cri);

        int total = searchService.searchTotal(cri);
        String keyword = cri.getKeyword();
        if (search.isEmpty()) {
            keyword = keyword + "에 대한 검색결과가 없습니다.";
            model.addAttribute("message", keyword);
            return "search/errorPage";
        } else {
            model.addAttribute("message", keyword);
            model.addAttribute("search", search);
            model.addAttribute("option", cri.getSearchOption());
            PageDTO pageDTO = new PageDTO(total, cri);
            log.info("isNext === >{}", pageDTO.isNext());
            log.info("isPrev === >{}", pageDTO.isPrev());
            model.addAttribute("pageMaker", pageDTO);

            return "search/searchPage";
        }


    }

    @RequestMapping("/searchList")
    public String searchPage(
            Model model
            , @ModelAttribute Criteria cri
    ) {


        List<SearchVO> search = searchService.search(cri);

        log.info("cri.getPageNum() =====> {}", cri.getPageNum());
        log.info("cri.getAmount() =====> {}", cri.getAmount());

        int total = searchService.searchTotal(cri);

        log.info("토탈 === > {}",total);
        String keyword = cri.getKeyword();
        if (search.isEmpty()) {
            keyword = keyword + "에 대한 검색결과가 없습니다.";
            model.addAttribute("message", keyword);
            return "search/errorPage";
        } else {

            model.addAttribute("message", keyword);
            model.addAttribute("search", search);
            model.addAttribute("option", cri.getSearchOption());
            PageDTO pageDTO = new PageDTO(total, cri);
            log.info("isNext === >{}", pageDTO.isNext());
            log.info("isPrev === >{}", pageDTO.isPrev());
            model.addAttribute("pageMaker", pageDTO);

            return "search/searchPage";
        }


    }

//    private byte decodedUrlToByte(String url) {
//
//        try {
//            url = URLDecoder.decode(url, "UTF-8");
//
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
//        url = url.substring(url.length() - 1);
//
//        return Byte.parseByte(url);
//
//    }

}
