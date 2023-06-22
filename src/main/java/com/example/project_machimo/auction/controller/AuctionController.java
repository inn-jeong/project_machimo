package com.example.project_machimo.auction.controller;


import com.example.project_machimo.auction.dto.AuctionDTO;
import com.example.project_machimo.auction.dto.BidsDTO;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.dto.ProductsDTO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

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

    @GetMapping("/action-list/{id}")
    public String showProduct(Model model, @PathVariable int id) {
        log.info("showProduct 여기로옴 ");
        System.out.println("@!#!@#$!@#@!#" + id);
        List<AuctionDTO> aList = auctionService.aList(id);
        List<ProductsDTO> pView = productService.pView(id);
        List<BidsDTO> bList = bidsService.bList(id);
        boolean hasBidHistory = bidsService.hasBidHistory(id);
        Integer amount = bidsService.maxAmount(id);

        System.out.println("동작 하나요?"+pView.get(0).productsId());
        model.addAttribute("aList", aList);
        model.addAttribute("pView", pView);
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("amount", amount);
        model.addAttribute("bList", bList);
         model.addAttribute("checkDTO", new CheckDTO());

        return "auctions/auctionsTest";
    }

    @GetMapping("/product")
    public String products(Model model) {
        List<ProductsDTO> productsDTOS = productService.pList();

        model.addAttribute("products", productsDTOS);

        return "auctions/productList";
    }

    @GetMapping("/action-list/test1Con")
    public String test1(@RequestParam int pro,Model model){
        System.out.println(pro);
        List<BidsDTO> bList = bidsService.bList(pro);
        List<AuctionDTO> aList = auctionService.aList(pro);
        boolean hasBidHistory = bidsService.hasBidHistory(pro);
        model.addAttribute("bList",bList);
        model.addAttribute("hasBidHistory",hasBidHistory);
        model.addAttribute("aList",aList);
        return "auctions/test";
    }

}
