package com.example.ordermanagement.services;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.customer.CustomerCreate;
import com.example.ordermanagement.dtos.customer.CustomerUpdate;
import com.example.ordermanagement.entities.CustomerEntity;
import com.example.ordermanagement.mappers.CustomerMapper;
import com.example.ordermanagement.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerService {

    @Resource
    CustomerRepository customerRepository;

    @Resource
    CustomerMapper customerMapper;

    public Customer addCustomer(CustomerCreate customerCreate) {
        CustomerEntity customerEntity = customerRepository.save(customerMapper.customerCreateToCustomerEntity(customerCreate));
        return customerMapper.customerEntityToCustomer(customerEntity);
    }

    public Customer getCustomer(Integer customerId) {
        return customerMapper.customerEntityToCustomer(customerRepository.findById(customerId).get());
    }

    public Customer customerUpdate(Integer customerId, CustomerUpdate customerUpdate) {
        CustomerEntity customerEntity = customerRepository.findById(customerId).get();
        customerEntity.setFullName(customerUpdate.getFullName());
        customerEntity.setRegistrationCode(customerUpdate.getRegistrationCode());
        customerEntity.setEmail(customerUpdate.getEmail());
        customerEntity.setTelephone(customerEntity.getTelephone());
        return customerMapper.customerEntityToCustomer(customerRepository.save(customerEntity));
    }

    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(customerId);
    }



}
