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
@Tag(name = "Customer: management")
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    @PostMapping("/add")
    @Operation(summary = "create new customer")
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
    public Customer updateCustomer (@PathVariable Integer customerId,
                                    @Valid @RequestBody CustomerUpdate customerUpdate){
        return customerService.customerUpdate(customerId, customerUpdate);
    }

    @DeleteMapping("/delete/{customerId}")
    @Operation(summary = "delete customer")
    public void deleteCustomer (@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
    }



}
