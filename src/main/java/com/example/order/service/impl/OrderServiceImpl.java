package com.example.order.service.impl;

import com.example.order.entity.Order;
import com.example.order.mapper.OrderMapper;
import com.example.order.model.ConsumptionRequest;
import com.example.order.model.EmailModel;
import com.example.order.model.OrderModel;
import com.example.order.model.TransactionRequest;
import com.example.order.repository.OrderRepository;
import com.example.order.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BankService bankService;
    private final CouponService couponService;
    private final NotificationService notificationService;
    private final StockService stockService;
    private final OrderMapper mapper;

    @Override
    public void createOrder(OrderModel orderModel) {
        log.info("Creating order: {}", orderModel);

        try {
           // log.info("check");
            if (stockService.checkAvailability(orderModel.getProduct_code())) {
                log.info("Product is available in stock. Proceeding with order creation.");

                Order order = mapper.toEntity(orderModel);

                ConsumptionRequest consumptionRequest = new ConsumptionRequest();
                consumptionRequest.setOrder_id((long) order.getId());
                consumptionRequest.setCoupon_code(order.getCoupon_code());
                consumptionRequest.setCustomer_email(order.getCustomerEmail());

                double amount = couponService.consumeCoupon(consumptionRequest);
                log.info("Coupon consumed successfully. Amount: {}", amount);

               ResponseEntity<String> consumeResponse = stockService.consume(order.getProduct_code());
               log.info("Stock consumed successfully. Response: {}", consumeResponse.getBody());

                TransactionRequest transactionRequestCustomer = new TransactionRequest();
                transactionRequestCustomer.setAmount(amount*order.getPrice());
                transactionRequestCustomer.setCardNumber(order.getCardNumber());
                ResponseEntity<String> withdrawResponse = bankService.withdraw(transactionRequestCustomer);
                log.info("Customer withdrawal request processed successfully. Response: {}", withdrawResponse.getBody());

                // Assuming merchant deposit is handled separately
                 TransactionRequest transactionRequestMerchant = new TransactionRequest();
                 transactionRequestMerchant.setCardNumber("5854894931571752");
                 transactionRequestMerchant.setAmount(amount*order.getPrice());
                 bankService.deposit(transactionRequestMerchant);
                 log.info(" merchant deposit done");
                 order.setCreationDate(LocalDate.now());
                 orderRepository.save(order);
                // Send notification
                EmailModel emailModel = new EmailModel();
                emailModel.setCreation(String.valueOf(order.getCreationDate()));
                emailModel.setPrice(String.valueOf(amount*order.getPrice()));
                sendEmail(order.getCustomerEmail(),emailModel);
                sendEmail("esraamabrouk126@gmail.com",emailModel);



            } else {
                log.warn("Product is not available in stock. Order creation aborted.");
            }
        } catch (Exception e) {
            log.error("Error occurred during order creation: {}", e.getMessage());
            // Handle error appropriately
        }
    }

   private void  sendEmail(String to ,EmailModel emailModel){
        emailModel.setTo(to);

       ResponseEntity<String> notificationResponse = notificationService.sendEmail(emailModel);
       log.info("Notification sent successfully to =>",to ,". Response: {}", notificationResponse.getBody());
    }

    @Override
    public List<Order> findOrderByCustomer_email(String customer_email) {
        return orderRepository.findAllByCustomerEmail(customer_email);
    }

    @Override
    public List<Order> findAllOrderBetween(LocalDate from, LocalDate to) {
        return orderRepository.findAllByCreationDateBetween(from, to);
    }
}
