package com.example.order.service.impl;

import com.example.order.model.EmailModel;
import com.example.order.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final RestTemplate restTemplate;
    private final String url="http://localhost:7070/email/send";
    @Override
    public ResponseEntity<String> sendEmail(EmailModel emailRequest) {
        return restTemplate.postForEntity(url, emailRequest, String.class);
    }
}
