package com.example.project_machimo.gyuha.auction.service;

import com.example.project_machimo.gyuha.auction.dto.CheckDTO;
import org.springframework.http.ResponseEntity;

public interface CheckResponseEntity {
    public ResponseEntity<?> getResponseEntityForCheck(CheckDTO check,Integer session);
}
