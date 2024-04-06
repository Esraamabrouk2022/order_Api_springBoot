package com.example.order.service;

import com.example.order.model.EmailModel;
import org.springframework.http.ResponseEntity;

public interface NotificationService {
     ResponseEntity<String> sendEmail( EmailModel emailRequest) ;

}
