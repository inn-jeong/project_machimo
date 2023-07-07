package com.example.project_machimo.gyuha.basket.Controller;

import com.example.project_machimo.gyuha.basket.dto.BasketDTO;
import com.example.project_machimo.gyuha.basket.service.BasketService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@Slf4j
public class BasketRestController {

    private final BasketService basketService;

    @Autowired
    public BasketRestController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/addBasket")
    public ResponseEntity<? extends Object> add(
            @RequestBody BasketDTO basketDTO
            , HttpSession session
    ) {
        log.info("장바구니에 들어온 제이슨 {}",basketDTO);
        if (session.getAttribute("userId")==null){
            return ResponseEntity.badRequest().body("로그인이 필요한 서비스입니다.");
        }

        Integer checkBasket = basketService.checkBasket(basketDTO);
        if (checkBasket == null) {

        }else {
            return ResponseEntity.badRequest().body("장바구니에 추가된 제품입니다.");
        }
        int addBasket = basketService.addBasket(basketDTO) ;
        if (addBasket==1){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body("장바구니 추가에 실패하였습니다.");
        }

    }
}
