package com.example.project_machimo.gyuha.order.controller;

import com.example.project_machimo.gyuha.order.vo.BuyProductVO;
import com.example.project_machimo.gyuha.order.vo.BuyerVO;
import com.example.project_machimo.gyuha.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
/*
-최규하
주문페이지에 데이터를 뿌려주는 컨트롤러
*/

@Slf4j
@Controller
@RequestMapping(("/order"))
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

/*

    -최규하
    제품 상세 페이지 및 장바구니에서 구매 버튼을 누를시 동작하는 컨트롤러 매소드
    제품 아이디를 List를 활용해서 디비에서 제품의 정보를 조회함 조회한 결과를
    뿌려줌

    */
    @PostMapping("/buyDirect")
    public String payment(
             Model model
            ,@RequestParam
             List<Integer> productId
            ,HttpSession session
    ) {

        for (Integer integer : productId) {
            log.info("프로덕트 아이디 {} ",integer);

        }

        BuyerVO user = orderService.getUser((Integer) session.getAttribute("userId"));

        List<BuyProductVO> buyList = orderService.getBuyList(productId);

        for (BuyProductVO buyProductVO : buyList) {
            System.out.println("가격 "+buyProductVO.pDirect());

        }

        model.addAttribute("getBuyList",orderService.getBuyList(productId));
        model.addAttribute("user",user);
        model.addAttribute("orderId",orderService.getOrderId());
        return "규리지앵";
    }

    @GetMapping("/complete")
    public String orderDone(
             HttpSession session
            ,Model model
            ,@RequestParam(name = "user_id") Integer userId
            ,@RequestParam(name = "order_id") Integer orderId
            ){

        Integer user;
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
