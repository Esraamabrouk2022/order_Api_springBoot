package com.example.order.service.impl;

import com.example.order.model.ConsumptionRequest;
import com.example.order.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final RestTemplate restTemplate;
   private final String url="http://localhost:8083/coupon/consume";
    @Override
    public Double consumeCoupon(ConsumptionRequest consumptionRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ConsumptionRequest> requestEntity = new HttpEntity<>(consumptionRequest, headers);

        ResponseEntity<Double> response = restTemplate.postForEntity(url, requestEntity, Double.class);

        return response.getBody();
    }
}
