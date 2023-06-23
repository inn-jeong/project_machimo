package com.example.project_machimo.auction.controller;


import com.example.project_machimo.auction.dto.AuctionDTO;
import com.example.project_machimo.auction.dto.BidsDTO;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.dto.ProductsDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.beans.Beans;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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



    private static String timeStampToString(ProductsDTO pView) {
        Timestamp timestamp = pView.pDur();
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일HH시mm분");
        String endDate = sdf.format(date);
        return endDate;
    }

    @GetMapping("/action-list/{id}")
    public String showProduct(Model model, @PathVariable int id) {
        log.info("showProduct 여기로옴 ");
        System.out.println("@!#!@#$!@#@!#" + id);
        AuctionDTO aList = auctionService.aList(id);
        ProductsDTO pView = productService.pView(id);
        System.out.println(pView.pBPrice()+"첫 가격");
        List<BidsDTO> bList = bidsService.bList(id);
        boolean hasBidHistory = bidsService.hasBidHistory(id);
        Long amount = bidsService.maxAmount(id);



        System.out.println(pView.pDur());
        model.addAttribute("aList", aList);
        model.addAttribute("pView", pView);
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("amount", amount);
        model.addAttribute("bList", bList);
        model.addAttribute("checkDTO", new CheckDTO());
        model.addAttribute("endDate", timeStampToString(pView));
        return "auctions/auctionsTest";
    }

    @GetMapping("/product")
    public String products(Model model) {
        List<ProductsDTO> productsDTOS = productService.pList();

        model.addAttribute("products", productsDTOS);

        return "auctions/productList";
    }

    @GetMapping("/action-list/test1Con")
    public String test1(@RequestParam int pro, Model model) {
        System.out.println(pro);
        List<BidsDTO> bList = bidsService.bList(pro);
        AuctionDTO aList = auctionService.aList(pro);
        boolean hasBidHistory = bidsService.hasBidHistory(pro);
        model.addAttribute("bList", bList);
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("aList", aList);
        return "auctions/test";
    }
}
