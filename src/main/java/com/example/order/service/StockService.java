package com.example.order.service;

import org.springframework.http.ResponseEntity;

public interface StockService {
    boolean checkAvailability(String product_code);
    ResponseEntity<String> consume(String product_code);
}
