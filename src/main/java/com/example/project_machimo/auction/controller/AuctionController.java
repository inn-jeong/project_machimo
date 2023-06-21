package com.example.project_machimo.auction.controller;


import com.example.project_machimo.auction.dto.AuctionDTO;
import com.example.project_machimo.auction.dto.BidsDTO;
import com.example.project_machimo.auction.dto.ProductsDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.Integer.*;

@Slf4j
@Controller
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private BidsService bidsService;

    @RequestMapping("/action-list")
    public String showProduct(Model model, HttpServletRequest request) {
        log.info("showProduct 여기로옴 ");
        int id = parseInt(request.getParameter("no"));
        System.out.println("@!#!@#$!@#@!#" + id);
        List<AuctionDTO> aList = auctionService.aList(id);
        
        List<ProductsDTO> pView = productService.pView(id);
        List<BidsDTO> bList = bidsService.bList(id);
        boolean hasBidHistory = bidsService.hasBidHistory(id);
        Integer amount = bidsService.maxAmount(id);
        model.addAttribute("aList", aList);
        model.addAttribute("pView", pView);
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("amount", amount);
        model.addAttribute("bList",bList);
        return "auctions/auctionsTest";
    }

    @GetMapping("/product")
    public String products(Model model) {
        List<ProductsDTO> productsDTOS = productService.pList();
        model.addAttribute("tlqkf", productsDTOS);

        return "auctions/productList";
    }

    @PostMapping("/amountCheck")
    public String updateAmount(HttpServletRequest request, Model model) {
        int bids = parseInt(request.getParameter("bids"));
        int productId = parseInt(request.getParameter("productId"));
        boolean bidsHistory = Boolean.parseBoolean(request.getParameter("bidsHistory"));
        int firstPrice = 0;
        if(request.getParameter("firstPrice") != null){
         firstPrice = parseInt(request.getParameter("firstPrice"));
        }

        Integer amount = bidsService.maxAmount(productId);


        if (bidsHistory) {
            if (firstPrice >= bids) {
                return "auctions/small";

            } else {
                bidsService.write(bids, productId,firstPrice);
                return "redirect:/auction/product";
            }

        } else {
            if (amount >= bids) {
                return "auctions/small";
            }else{
                bidsService.amountUpdate(bids,productId);
                auctionService.highestBidUpdate(bids,productId);
                return "redirect:/auction/product";
            }
        }

    }
}
