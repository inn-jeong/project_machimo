package com.example.project_machimo.order.controller;

import com.example.project_machimo.order.dto.BuyerVO;
import com.example.project_machimo.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping(("/order"))
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/buyDirect")
    public String payment(
             Model model
            ,@RequestParam
             List<Integer> productId
            ,HttpSession session
    ) {
        BuyerVO user = orderService.getUser((Integer) session.getAttribute("userId"));
        model.addAttribute("getBuyList",orderService.getBuyList(productId));
        model.addAttribute("user",user);
        model.addAttribute("orderId",orderService.getOrderId());
        return "order/payment";
    }

    @GetMapping("/checkSession")
    public ResponseEntity<?> request(HttpSession session){
        Integer user =(Integer) session.getAttribute("userId");
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.ok().build();


    }
    @GetMapping("/complete")
    public String orderDone(
             HttpSession session
            ,Model model
            ,@RequestParam(name = "user_id") Integer userId
            ,@RequestParam(name = "order_id") Integer orderId
            ){

        Integer user;
        System.out.println(session.getAttribute("userId")+"세션이름ㄴㅇㅁㄴㄹㅇㄴㅁ");
        System.out.println(userId+"유저이이디 이름 ");
        System.out.println("오더 아읻"+orderId);
        if (session.getAttribute("userId")!=null){

             user=(Integer) session.getAttribute("userId");
        }else {
            user = 0;
        }
        if (!Objects.equals(user, userId)){
            return "fail";
        }

        int orderProductTotal = orderService.getOrderProductTotal(orderId);

        model.addAttribute("total",orderProductTotal);



        return "order/complete";

    }
}
