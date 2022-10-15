package com.example.ordermanagement.apis;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.customer.CustomerCreate;
import com.example.ordermanagement.dtos.customer.CustomerUpdate;
import com.example.ordermanagement.dtos.order.Order;
import com.example.ordermanagement.dtos.order.OrderCreate;
import com.example.ordermanagement.dtos.order.OrderUpdate;
import com.example.ordermanagement.services.CustomerService;
import com.example.ordermanagement.services.OrderService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Order: management")
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/add")
    @Operation(summary = "create new order")
    public Order createOrder(@Valid @RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "find order by orderId")
    public Order getOrder(@Valid @PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }


    @PutMapping("/update")
    @Operation(summary = "update existing order")
    public Order updateOrder(@Valid @RequestBody OrderUpdate orderUpdate) {
        return orderService.updateOrder(orderUpdate);
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "delete existing order")
    public void deleteOrder(@Valid @PathVariable Integer orderId) {
      orderService.deleteOrder(orderId);
    }

    @DeleteMapping("/delete/orderline/{orderLineId}")
    @Operation(summary = "delete orderline from order")
    public void deleteOrderLineFromOrder(@Valid @PathVariable Integer orderLineId) throws Exception {
        orderService.deleteOrderLine(orderLineId);
    }

    @GetMapping("/orders/customer/{customerId}")
    @Operation(summary = "find all orders by customer id")
    public List<Order> findOrdersByCustomerId(@Valid @PathVariable Integer customerId) {
         return orderService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/orders/product{productId}")
    @Operation(summary = "find all orders by productId")
    public List<Order> findOrdersByProductId(@Valid @PathVariable Integer productId) {
        return orderService.findOrdersByProductId(productId);
    }

    @GetMapping("/orders/date")
    @Operation(summary = "find all orders by date")
    public List<Order> findOrdersByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return orderService.findOrdersByDate(date);
    }
}
