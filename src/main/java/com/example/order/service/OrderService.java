package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.model.OrderModel;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    public void createOrder(OrderModel orderModel);
    public List<Order> findOrderByCustomer_email(String customer_email);
    public List<Order> findAllOrderBetween(LocalDate from,LocalDate to);
}
