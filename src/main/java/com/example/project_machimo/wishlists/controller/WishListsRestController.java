package com.example.project_machimo.wishlists.controller;


import com.example.project_machimo.wishlists.dto.WishlistsDTO;
import com.example.project_machimo.wishlists.service.WishListsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlists")
@Slf4j
public class WishListsRestController {

    private final WishListsService wishListsService;
    @Autowired
    public WishListsRestController(WishListsService wishListsService) {
        this.wishListsService = wishListsService;

    }

    @PostMapping("/insert")
    public ResponseEntity<?> ins(@RequestBody WishlistsDTO wishlistsDTO){
        log.info("#insert json ==> {}",wishlistsDTO);
        int i = wishListsService.insertWish(wishlistsDTO);

        if (i==1){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
    @PostMapping("/delete")
    public ResponseEntity<?> del(@RequestBody WishlistsDTO wishlistsDTO){

        log.info("#delete json ==> {}",wishlistsDTO);

        int i = wishListsService.deleteWish(wishlistsDTO);

        if (i==1){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
