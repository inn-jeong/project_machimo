package com.example.project_machimo.search.controller;

import com.example.project_machimo.search.dto.Criteria;
import com.example.project_machimo.search.dto.PageDTO;
import com.example.project_machimo.search.dto.SearchVO;
import com.example.project_machimo.search.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    public String search(@RequestParam byte searchOption
            , @RequestParam String keyword
            , Model model
            , Criteria cri
    ) {

        List<SearchVO> search = searchService.search(searchOption, keyword, cri);

        int total = searchService.searchTotal(keyword);
        if (search.isEmpty()) {
            keyword = keyword + "에 대한 검색결과가 없습니다.";
            model.addAttribute("message", keyword);
            return "search/errorPage";
        } else {
            model.addAttribute("message", keyword);
            model.addAttribute("search", search);
            model.addAttribute("pageMaker", new PageDTO(total, cri));

            return "search/searchPage";
        }


    }

    @RequestMapping("/searchList")
    public String search(@RequestParam int pageNum
            , @RequestParam int amount
            , @RequestParam String searchOption
            , @RequestParam String keyword
            , Model model
            , @ModelAttribute Criteria cri
    ) {
        System.out.println(searchOption);



        byte dSearchOption = decodedUrlToByte(searchOption);
        List<SearchVO> search = searchService.search(dSearchOption, keyword, cri);
        log.info("dSearchOption ==> {}",dSearchOption);
        log.info("cri.getPageNum() =====> {}" ,cri.getPageNum() );
        log.info("cri.getAmount() =====> {}" ,cri.getAmount() );

        int total = searchService.searchTotal(keyword);
        if (search.isEmpty()) {
            keyword = keyword + "에 대한 검색결과가 없습니다.";
            model.addAttribute("message", keyword);
            return "search/errorPage";
        } else {
            model.addAttribute("message", keyword);
            model.addAttribute("search", search);
            model.addAttribute("option", searchOption);
            model.addAttribute("pageMaker", new PageDTO(total, cri));

            return "search/searchPage";
        }


    }

    private byte decodedUrlToByte(String url) {

        try {
            url = URLDecoder.decode(url, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        url = url.substring(url.length() - 1);

        return Byte.parseByte(url);

    }

}
