package com.example.order.repository;

import com.example.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
     List<Order> findAllByCustomerEmail(String customer_email);
     List<Order> findAllByCreationDateBetween(LocalDate from, LocalDate to);
}
