package com.example.project_machimo.productEnroll.controller;


import com.example.project_machimo.productEnroll.service.EnrollService;
import com.example.project_machimo.shop.Dto.CategoryDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/productEnroll")
public class EnrollController {

    @Autowired
    private EnrollService service;

    @RequestMapping("/enroll_form")
    public String enroll_form(Model model){
        //        // 모든 카테고리와 그에 해당하는 하위 카테고리를 가져옴
        ArrayList<CategoryDto> categories = service.getCategories();
        Map<Integer, ArrayList<CategoryDto>> subcategory = new HashMap<>();
        for (CategoryDto category : categories) {
            ArrayList<CategoryDto> subCategories = service.getSubCategories(category.getCId());
            subcategory.put(category.getCId(), subCategories);
        }

        //        카테고리 값을 model에 저장 하고 넘어감
        model.addAttribute("categories", categories);

//        Map안에 배열을 담은 형태
        model.addAttribute("subcategory", subcategory);
//        log.info("@# category ===>"+categories.get(0).getCId());
//        log.info("@# category ===>"+model.getAttribute("categories"));

        return "productEnroll/soldForm";
    }

//    @RequestMapping("/enroll_form")
//    public String enroll_form( , @RequestParam(name = "category2", required = false) Integer cId
//            ,@RequestParam(name = "category1", required = false) Integer cId2){
//        return "productEnroll/soldForm";
//    }

    @RequestMapping("/enroll")
    public String enroll(@RequestParam HashMap<String, String> param){

//
//        //        카테고리 값을 model에 저장 하고 넘어감
//        model.addAttribute("categories", categories);
//        model.addAttribute("subcategory", subcategory);

        service.write(param);
        return "productEnroll/success";
    }


    @RequestMapping("/jusoPopup")
    public String jusoPopup(HttpServletRequest request, Model model){
        //request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
        //request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
        log.info("@# =========juso init============");
        String inputYn = request.getParameter("inputYn");
        String roadFullAddr = request.getParameter("roadFullAddr");
        String roadAddrPart1 = request.getParameter("roadAddrPart1");

        String roadAddrPart2 = request.getParameter("roadAddrPart2");
        String engAddr = request.getParameter("engAddr");
        String jibunAddr = request.getParameter("jibunAddr");
        String zipNo = request.getParameter("zipNo");
        String addrDetail = request.getParameter("addrDetail");
        String admCd    = request.getParameter("admCd");
        String rnMgtSn = request.getParameter("rnMgtSn");
        String bdMgtSn  = request.getParameter("bdMgtSn");
        /** API 서비스 제공항목 확대 (2017.02) **/
        String detBdNmList  = request.getParameter("detBdNmList");
        String bdNm  = request.getParameter("bdNm");
        String bdKdcd  = request.getParameter("bdKdcd");
        String siNm  = request.getParameter("siNm");
        String sggNm  = request.getParameter("sggNm");
        String emdNm  = request.getParameter("emdNm");
        String liNm  = request.getParameter("liNm");
        String rn  = request.getParameter("rn");
        String udrtYn  = request.getParameter("udrtYn");
        String buldMnnm  = request.getParameter("buldMnnm");
        String buldSlno  = request.getParameter("buldSlno");
        String mtYn  = request.getParameter("mtYn");
        String lnbrMnnm  = request.getParameter("lnbrMnnm");
        String lnbrSlno  = request.getParameter("lnbrSlno");
        String emdNo  = request.getParameter("emdNo");

        log.info("@# juso input ===> "+inputYn);
        log.info("@# juso part1 ===> "+roadAddrPart1);

        model.addAttribute("inputYn",inputYn);
        model.addAttribute("roadAddrPart1",roadAddrPart1);
        model.addAttribute("addrDetail",addrDetail);
        model.addAttribute("zipNo",zipNo);


        return "productEnroll/jusoPopup";
    }
}
