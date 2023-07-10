package com.example.project_machimo.Park_gi_hyeon.shop.controller;

import com.example.project_machimo.Park_gi_hyeon.shop.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/shop")
public class shopRestController {
    @Autowired
    private ShopService service;


}