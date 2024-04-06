package com.example.order.service.impl;

import com.example.order.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final RestTemplate restTemplate;
    private final String availabilityUrl = "http://localhost:8082/stock/checkAvailability?storeId=1&productCode=";
    private final String consumeUrl = "http://localhost:8082/stock/consume?storeId=1&productCode=";

    @Override
    public boolean checkAvailability(String product_code) {
        ResponseEntity<Boolean> response = restTemplate.getForEntity(availabilityUrl + product_code, Boolean.class);

        Boolean availability = response.getBody();

        return  availability;
    }

    @Override
    public ResponseEntity<String> consume(String product_code) {
        ResponseEntity<String> response = restTemplate.getForEntity(consumeUrl + product_code, String.class);
        return response;
    }


}
