package com.example.order.service;

import com.example.order.model.TransactionRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BankService {

     ResponseEntity<String> deposit( TransactionRequest transactionRequest);
     ResponseEntity<String> withdraw(TransactionRequest transactionRequest);

}
