package com.example.project_machimo.gyuha.order.controller;

import com.example.project_machimo.gyuha.aop.LoginCheck;
import com.example.project_machimo.gyuha.order.dto.OrderDTO;
import com.example.project_machimo.gyuha.order.dto.PaymentJsonDTO;
import com.example.project_machimo.gyuha.order.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/*
-최규하
REST API를 사용하여 데이터를 주고 받는 Rest 컨트롤러
*/
@RestController
@Slf4j
@RequestMapping("/payment")

public class OrderRestController {


//    아임포트 API키
    private final String IMAPORT_REST_API_KEY = "6181664231655157";
    private final String IMAPORT_REST_API_SECRET = "cHWubAwNZnM9ZwZmLTqo2prSdPMbBrdjAz057Ivcz1ffW9TeBeHXIgym88opNwExhPvTNfjlT7Kxs0t0";

    private IamportClient iamportClient ;

    @Autowired
    private  OrderService orderService;




    public OrderRestController( ) {
        this.iamportClient  = new IamportClient(IMAPORT_REST_API_KEY,IMAPORT_REST_API_SECRET);
    }

    
    //세션 체크
    @PostMapping("/checkSession")
    @LoginCheck
    public ResponseEntity<?> request(HttpSession session){


        return ResponseEntity.ok().build();
    }

    //아임포트에 결제내역을 insert 하는 컨트롤러 메소드
    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String impUid)
            throws IamportResponseException, IOException {

        log.info("impUid == > {}",impUid);
        return iamportClient.paymentByImpUid(impUid);
    }

//    아임포트에 주문을 취소하는 컨트롤러 메소드
    @RequestMapping("/cancel")
    public IamportResponse<Payment> cancelIamport(@RequestBody PaymentJsonDTO jsonDTO) throws IamportResponseException, IOException {
           CancelData cancelData = new CancelData(jsonDTO.getImpUid(),true);
           return iamportClient.cancelPaymentByImpUid(cancelData);
    }

    /*
    아임포트에서 결과가 insert되고 실행되는 ajax에서 결과를 요청하는 url에 있는 주소
    db의 제품 가격및 유저가 가지고 있는 포인트를 비교해서 
    구매할때 가격 및 사용한 포인트와 비교해서 응답을 내림
    */
    @RequestMapping("/success")
    public ResponseEntity<? extends Object> response(@RequestBody OrderDTO orderDTO){

        int amountResult = orderService.getAmountResult(orderDTO.getProductIdList());
        
        int userPoint = orderService.getUserPoint(orderDTO.getUserId());

        int usedPointResult = orderDTO.getUsedPointResult();
        int orderPrice = orderDTO.getOrderPrice();

        int orderTotalPrice = amountResult-usedPointResult;


        log.info("어먼트 리절트 = "+amountResult+"주문값 = "+orderPrice);
        log.info("유저 포인트 = "+userPoint+"사용한 포인트"+usedPointResult);
        int response =orderService.response(orderDTO);
        if (response!=2){
            return ResponseEntity.badRequest().build();
        }
        
        if (orderPrice!=orderDTO.getOrderPrice()){
            return ResponseEntity.badRequest().build();
        }
        
        if (userPoint >= orderDTO.getUsedPointResult()){
            List<Integer> productIdList = orderDTO.getProductIdList();
            for (Integer productId : productIdList) {
            orderService.completed(orderDTO,productId);
            }
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    /*
    주문이 실패되고 db에 다시 내역들을 돌려놓는 컨트롤러 매소드
    위에 코드가 실행되고 db에 데이터와 결제금액 및 유저가 사용한 포인트와 가지고 있는 포인트가 다를시
    ajax가 이 주소로 요청을 보냄
    */
    @RequestMapping("cancel-db")
    public ResponseEntity<?> cancel(@RequestBody OrderDTO orderDTO){
        List<Integer> productIdList = orderDTO.getProductIdList();
        int result = orderService.cancelDb(orderDTO)+productIdList.size();

        int i = 3 + productIdList.size();
        if (result==i){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build();
        }

    }


}

