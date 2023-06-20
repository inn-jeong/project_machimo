package com.example.project_machimo.auction.controller;


import com.example.project_machimo.auction.dto.AuctionDTO;
import com.example.project_machimo.auction.dto.ProductsDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.Integer.*;

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @RequestMapping("/action-list")
    public String showProduct(Model model, HttpServletRequest request){

        int id = parseInt(request.getParameter("no"));
        System.out.println("@!#!@#$!@#@!#"+id);
        List<AuctionDTO> auctionDTOS = auctionService.aList(id);

        model.addAttribute("list",auctionDTOS);



        return "auotionsTest";
    }

    @GetMapping("/product")
    public String products(Model model){
        List<ProductsDTO> productsDTOS = productService.pList();
        model.addAttribute("tlqkf",productsDTOS);

        return "productList";
    }
}
