package com.example.order.resource;

import com.example.order.entity.Order;
import com.example.order.model.OrderModel;
import com.example.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderResource {
    private final OrderService orderService;

    @PostMapping("create/WithoutCoupon")
    public void createOrderWithoutCoupon(@Valid @RequestBody OrderModel orderModel){
        orderService.createOrder(orderModel);
    }
    @PostMapping("create/WithCoupon")
    public void createOrderWithCoupon(@Valid @RequestBody OrderModel orderModel){
        orderService.createOrder(orderModel);
    }
    @GetMapping("findByCustomerEmail")
    public List<Order> findOrderByCustomerEmail(@Valid@RequestBody String customerEmail){
        return orderService.findOrderByCustomer_email(customerEmail);
    }
    @GetMapping("ByDateRange")
    public List<Order> searchByDateRange(
            @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate from,
            @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate to
            ){
        return orderService.findAllOrderBetween(from,to);
    }
}
