package com.example.ordermanagement.apis;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.customer.CustomerCreate;
import com.example.ordermanagement.dtos.customer.CustomerUpdate;
import com.example.ordermanagement.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@Tag(name = "Order: management")
@RequestMapping("/order")
public class OrderController {

    @Resource
    CustomerService customerService;

    @PostMapping("/add")
    @Operation(summary = "add new customer")
    public Customer addCustomer (@Valid @RequestBody CustomerCreate customerCreate){
        return customerService.addCustomer(customerCreate);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "get customer from database")
    public Customer getCustomer (@PathVariable  Integer customerId){
        return customerService.getCustomer(customerId);
    }

    @PutMapping("/update/{customerId}")
    @Operation(summary = "update customer info")
    public Customer updateCustomer (@PathVariable Integer customerId, @Valid @RequestBody CustomerUpdate customerUpdate){
        return customerService.customerUpdate(customerId, customerUpdate);
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "delete order")
    public void deleteCustomer (@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
    }



}
