package com.example.ordermanagement.apis;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Tag(name = "Customer: management")
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    @PostMapping("/add")
    @Operation(summary = "add new customer")
    public Customer


}
