package com.example.project_machimo.order.controller;

import com.example.project_machimo.order.dto.OrderDTO;
import com.example.project_machimo.order.dto.PaymentJsonDTO;
import com.example.project_machimo.order.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class OrderRestController {


    private final String IMAPORT_REST_API_KEY = "6181664231655157";
    private final String IMAPORT_REST_API_SECRET = "cHWubAwNZnM9ZwZmLTqo2prSdPMbBrdjAz057Ivcz1ffW9TeBeHXIgym88opNwExhPvTNfjlT7Kxs0t0";

    private IamportClient iamportClient ;

    @Autowired
    private  OrderService orderService;




    public OrderRestController( ) {
        this.iamportClient  = new IamportClient(IMAPORT_REST_API_KEY,IMAPORT_REST_API_SECRET);
    }

    @RequestMapping("/verify/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable("imp_uid") String impUid)
            throws IamportResponseException, IOException {

        System.out.println(impUid);
        return iamportClient.paymentByImpUid(impUid);
    }

    @RequestMapping("/cancel")
    public IamportResponse<Payment> cancelIamport(@RequestBody PaymentJsonDTO jsonDTO) throws IamportResponseException, IOException {
           CancelData cancelData = new CancelData(jsonDTO.getImpUid(),true);
           return iamportClient.cancelPaymentByImpUid(cancelData);
    }
    @RequestMapping("/success")
    public ResponseEntity<? extends Object> response(@RequestBody OrderDTO orderDTO){
        int response = orderService.response(orderDTO);

        int amountResult = orderService.getAmountResult(orderDTO.getProductIdList());

        int userPoint = orderService.getUserPoint(orderDTO.getUserId());

        int usedPointResult = orderDTO.getUsedPointResult();
        int orderPrice = orderDTO.getOrderPrice();

        int orderTotalPrice = amountResult-usedPointResult;
        System.out.println("어먼트 리절트 = "+amountResult+"주문값 = "+orderPrice);

        System.out.println("유저 포인트 = "+userPoint+"사용한 포인트"+usedPointResult);
        if (response!=3){
            return ResponseEntity.badRequest().build();
        }

        if (orderPrice!=orderDTO.getOrderPrice()){
            return ResponseEntity.badRequest().build();
        }

        if (userPoint >= orderDTO.getUsedPointResult()){
            return ResponseEntity.ok().build();
        }else {
            List<Integer> productIdList = orderDTO.getProductIdList();
            for (Integer productId : productIdList) {
            orderService.completed(orderDTO,productId);

            }
            return ResponseEntity.badRequest().build();
        }
    }


}

