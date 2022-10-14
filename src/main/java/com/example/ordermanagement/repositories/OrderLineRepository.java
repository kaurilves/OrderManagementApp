package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.OrderLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer> {

    @Query("select o from OrderLineEntity o where o.orderEntity.id = ?1")
    List<OrderLineEntity> getOrderLinesByOrderId(Integer id);

    @Query("select o from OrderLineEntity o where o.productEntity.id = ?1")
    List<OrderLineEntity> findOrderLinesByProductId(Integer id);



}
