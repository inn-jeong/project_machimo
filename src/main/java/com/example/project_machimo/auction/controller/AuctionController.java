package com.example.project_machimo.auction.controller;


import com.example.project_machimo.auction.dto.AuctionVO;
import com.example.project_machimo.auction.dto.BidsVO;
import com.example.project_machimo.auction.dto.CheckDTO;
import com.example.project_machimo.auction.dto.ProductsVO;
import com.example.project_machimo.auction.service.AuctionService;
import com.example.project_machimo.auction.service.BidsService;
import com.example.project_machimo.auction.service.ProductService;
import com.example.project_machimo.wishlists.dao.WishListsDAO;
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

    @Autowired


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
        log.info("@#첫 가격은  === > {}", pView.pBPrice());
        List<BidsVO> bList = bidsService.bList(id);
        boolean hasBidHistory = bidsService.hasBidHistory(id);
        Long amount = bidsService.maxAmount(id);

        Integer integer = wishListsDAO.likeCheck(userId, id);

        boolean isLiked = integer != null;

        model.addAttribute("sss",userId);
        model.addAttribute("isLiked", isLiked);
        model.addAttribute("aList", aList);
        model.addAttribute("pView", pView);
        model.addAttribute("isSaleEnded", isSaleEnded(pView.pDur(), pView.pSalesStatus()));
        model.addAttribute("hasBidHistory", hasBidHistory);
        model.addAttribute("amount", amount);
        model.addAttribute("bList", bList);
        model.addAttribute("checkDTO", new CheckDTO());
        model.addAttribute("endDate", timeStampToString(pView));
        return "auctions/auctionsTest";
    }

    @GetMapping("/product")
    public String products(Model model) {
        List<ProductsVO> productsVOS = productService.pList();

        model.addAttribute("products", productsVOS);

        return "auctions/productList";
    }


    private boolean isSaleEnded(Timestamp period, int productStatus) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        switch (productStatus) {
            case 2, 3, 4 -> {
                if (period.before(timestamp)) return true;
            }
        }
        return false;

    }

}
