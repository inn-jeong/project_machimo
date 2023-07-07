package com.example.project_machimo.gyuha.wishlists.controller;


import com.example.project_machimo.gyuha.aop.LoginCheck;
import com.example.project_machimo.gyuha.wishlists.dto.WishlistsDTO;
import com.example.project_machimo.gyuha.wishlists.service.WishListsService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// 최규하 - auction 찜기능 처리하는 컨트롤러
@RestController
@RequestMapping("/wishlists")
@Slf4j
public class WishListsRestController {

    private final WishListsService wishListsService;

    @Autowired
    public WishListsRestController(WishListsService wishListsService) {
        this.wishListsService = wishListsService;

    }


    /* - 최규하
      찜 테이블에 정보를 저장하는 컨트롤러 메소드
      찜 테이블에 인서트를 성공하면 i의 값이 1이됨
      i의 값이 1이라면 ok를 내려주고 아니라면 badRequest를 내려줌*/
    @LoginCheck
    @PostMapping("/insert")
    public ResponseEntity<?> ins(@RequestBody WishlistsDTO wishlistsDTO, HttpSession session) {
        log.info("#insert json ==> {}", wishlistsDTO);
        int i = wishListsService.insertWish(wishlistsDTO);

        if (i == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }


    }


    /* 최규하
     찜 삭제 하는 메소드 컨트롤러
     찜이 삭제가되어서 i의 값이 1이면 Ok를 내려주고 아니면 BadRequst를 내려줌
     */
    @PostMapping("/delete")
    public ResponseEntity<?> del(@RequestBody WishlistsDTO wishlistsDTO) {

        log.info("#delete json ==> {}", wishlistsDTO);

        int i = wishListsService.deleteWish(wishlistsDTO);

        if (i == 1) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


    }
}
