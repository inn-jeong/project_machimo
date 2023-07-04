//package com.example.project_machimo.wishlists.controller;
//
//
//import com.example.project_machimo.wishlists.dto.WishlistsDTO;
//import com.example.project_machimo.wishlists.service.WishListsService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/wishlists")
//@Slf4j
//public class WishListsRestController {
//
//    private final WishListsService wishListsService;
//    @Autowired
//    public WishListsRestController(WishListsService wishListsService) {
//        this.wishListsService = wishListsService;
//    }
//
//    @PostMapping("/check")
//    public ResponseEntity<? extends Object> response(
//            @RequestBody WishlistsDTO wishlistsDTO
//    ){
//      log.info("위시리스트로온 제이슨 {}",wishlistsDTO);
//
//        boolean b = wishListsService.hasWishLists(wishlistsDTO);
//
//        if (b){
//            ResponseEntity.ok().body(b);
//            ResponseEntity.status()
//        }
//
//
//
//        return null;
//    }
//}
