package com.example.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRequest {
    private Long order_id;
    private double amount=1;
    private String customer_email;
    private String coupon_code;
}
