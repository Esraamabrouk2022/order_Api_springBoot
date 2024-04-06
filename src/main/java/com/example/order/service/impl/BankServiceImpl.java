package com.example.order.service.impl;

import com.example.order.model.TransactionRequest;
import com.example.order.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final RestTemplate restTemplate;
    private final String depositUrl = "http://localhost:8081/bank/transaction/deposit";
    private final String withdrawUrl = "http://localhost:8081/bank/transaction/withdraw";

    @Override
    public ResponseEntity<String> deposit(TransactionRequest transactionRequest) {
        return restTemplate.postForEntity(depositUrl, transactionRequest, String.class);
    }

    @Override
    public ResponseEntity<String> withdraw(TransactionRequest transactionRequest) {
        return restTemplate.postForEntity(withdrawUrl, transactionRequest, String.class);
    }
}


