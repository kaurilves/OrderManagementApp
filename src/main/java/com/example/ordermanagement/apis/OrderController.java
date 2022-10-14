package com.example.ordermanagement.apis;

import com.example.ordermanagement.dtos.customer.Customer;
import com.example.ordermanagement.dtos.customer.CustomerCreate;
import com.example.ordermanagement.dtos.customer.CustomerUpdate;
import com.example.ordermanagement.dtos.order.Order;
import com.example.ordermanagement.dtos.order.OrderCreate;
import com.example.ordermanagement.dtos.order.OrderUpdate;
import com.example.ordermanagement.services.CustomerService;
import com.example.ordermanagement.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Order: management")
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/add")
    @Operation(summary = "create new order")
    public Order createOrder(@RequestBody OrderCreate orderCreate) {
        return orderService.createOrder(orderCreate);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "find order by orderId")
    public Order getOrder(@PathVariable Integer orderId) {
        return orderService.getOrder(orderId);
    }


    @PutMapping("/update")
    @Operation(summary = "update existing order")
    public Order updateOrder(@RequestBody OrderUpdate orderUpdate) {
        return orderService.updateOrder(orderUpdate);
    }

    @DeleteMapping("/delete/{orderId}")
    @Operation(summary = "delete existing order")
    public void deleteOrder(@PathVariable Integer orderId) {
      orderService.deleteOrder(orderId);
    }

    @DeleteMapping("/delete/orderline/{orderLineId}")
    @Operation(summary = "delete orderline from order")
    public void deleteOrderLineFromOrder(@PathVariable Integer orderLineId) throws Exception {
        orderService.deleteOrderLine(orderLineId);
    }

    @GetMapping("/orders/customer/{customerId}")
    @Operation(summary = "find all orders by customer id")
    public List<Order> findOrdersByCustomerId(@PathVariable Integer customerId) {
         return orderService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/orders/product{customerId}")
    @Operation(summary = "find all orders by productId")
    public List<Order> findOrdersByProductId(@PathVariable Integer productId) {
        return orderService.findOrdersByProductId(productId);
    }


}
