package com.example.project_machimo.gyuha.auction.controller;


import com.example.project_machimo.gyuha.auction.vo.AuctionVO;
import com.example.project_machimo.gyuha.auction.vo.BidsVO;
import com.example.project_machimo.gyuha.auction.vo.ProductsVO;
import com.example.project_machimo.gyuha.auction.service.AuctionService;
import com.example.project_machimo.gyuha.auction.service.BidsService;
import com.example.project_machimo.gyuha.auction.service.ProductService;
import com.example.project_machimo.gyuha.wishlists.dao.WishListsDAO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/auction")
public class AuctionController {


    final private AuctionService auctionService;

    final private ProductService productService;

    final private BidsService bidsService;

    final private WishListsDAO wishListsDAO;


    @Autowired
    public AuctionController(
            AuctionService auctionService
            , ProductService productService
            , BidsService bidsService
            , WishListsDAO wishListsDAO


    ) {
        this.auctionService = auctionService;
        this.productService = productService;
        this.bidsService = bidsService;
        this.wishListsDAO = wishListsDAO;

    }



    private static String timeStampToString(ProductsVO pView) {
        Timestamp timestamp = pView.pDur();
        Date date = new Date(timestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일");
        String endDate = sdf.format(date);
        return endDate;
    }

    @GetMapping("/action-list/{id}")
    public String showProduct(Model model, @PathVariable int id, HttpSession session) {
        Integer userId = Optional.ofNullable((Integer) session.getAttribute("userId")).orElse(0);
        log.info("유저아이디는 ===> {}",userId);
        log.info("showProduct 여기로옴 ");
        System.out.println("@!#!@#$!@#@!#" + id);


        AuctionVO aList = auctionService.aList(id);
        ProductsVO pView = productService.pView(id);
        List<BidsVO> bList = bidsService.bList(id);
        boolean hasBidHistory = bidsService.hasBidHistory(id);
        Long amount = bidsService.maxAmount(id);
        Integer integer = wishListsDAO.likeCheck(userId, id);
        auctionService.updateHit(id);

        log.info("@#첫 가격은  === > {}", pView.pBPrice());
        boolean saleEnded = auctionService.isSaleEnded(pView.pDur(), pView.pSalesStatus());
        boolean isLiked = integer != null;
        int sellerId = auctionService.getUserId(pView.productsId());
        System.out.println("일러 아이디"+sellerId);

        model.addAttribute("sellerId",sellerId);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("aList", aList);
        model.addAttribute("pView", pView);
        model.addAttribute("isSaleEnded", saleEnded);
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("amount", amount);
        model.addAttribute("bList", bList);
        model.addAttribute("endDate", timeStampToString(pView));
        return "auctions/auction";
    }

    @GetMapping("/product")
    public String products(Model model) {
        List<ProductsVO> productsVOS = productService.pList();

        model.addAttribute("products", productsVOS);

        return "auctions/productList";
    }




}
