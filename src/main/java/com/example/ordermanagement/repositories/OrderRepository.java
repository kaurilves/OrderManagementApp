package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select o from OrderEntity o where o.customerEntity.id = ?1")
    List<OrderEntity> findOrdersByCustomerId(Integer id);


}