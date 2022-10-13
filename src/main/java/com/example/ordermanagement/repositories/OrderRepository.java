package com.example.ordermanagement.repositories;

import com.example.ordermanagement.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CustomerEntity, Integer> {
}