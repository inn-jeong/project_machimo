package com.example.project_machimo.order.service;

import com.example.project_machimo.order.dao.OrderDAO;
import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final OrderDAO orderDAO;
    private final String IMAPORT_REST_API_KEY = "6181664231655157";
    private final String IMAPORT_REST_API_SECRET = "cHWubAwNZnM9ZwZmLTqo2prSdPMbBrdjAz057Ivcz1ffW9TeBeHXIgym88opNwExhPvTNfjlT7Kxs0t0";

    private  IamportClient client;

    @Autowired
    public PaymentServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }




}
