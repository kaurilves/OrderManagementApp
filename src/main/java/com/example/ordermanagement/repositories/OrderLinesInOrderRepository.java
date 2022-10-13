package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.OrderLinesInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLinesInOrderRepository extends JpaRepository<OrderLinesInOrder, Integer> {
}