package com.example.ordermanagement.mappers;

import com.example.ordermanagement.dtos.order.Order;
import com.example.ordermanagement.dtos.order.OrderCreate;
import com.example.ordermanagement.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "customerId", target = "customerEntity.id")
    OrderEntity orderCreateToOrderEntity(OrderCreate orderCreate);

    Order orderEntityToOrder(OrderEntity orderEntity);

    List<Order> orderEntitiesToOrders(List<OrderEntity> orderEntity);
}
