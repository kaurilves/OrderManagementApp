package com.example.ordermanagement.mappers;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.customer.CustomerCreate;
import com.example.ordermanagement.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {

    CustomerEntity customerCreateToCustomerEntity(CustomerCreate customerCreate);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);

}
