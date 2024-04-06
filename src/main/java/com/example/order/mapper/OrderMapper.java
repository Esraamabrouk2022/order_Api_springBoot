package com.example.order.mapper;

import com.example.order.entity.Order;
import com.example.order.model.OrderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderModel orderModel);
    OrderModel requestToEntity(Order order);
}
