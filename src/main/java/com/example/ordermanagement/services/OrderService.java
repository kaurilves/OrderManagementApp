package com.example.ordermanagement.services;

import com.example.ordermanagement.dtos.order.Order;
import com.example.ordermanagement.dtos.order.OrderCreate;
import com.example.ordermanagement.dtos.order.OrderUpdate;
import com.example.ordermanagement.dtos.order.orderline.OrderLine;
import com.example.ordermanagement.dtos.order.orderline.OrderLineCreate;
import com.example.ordermanagement.dtos.order.orderline.OrderLineUpdate;
import com.example.ordermanagement.entities.OrderEntity;
import com.example.ordermanagement.entities.OrderLineEntity;
import com.example.ordermanagement.mappers.OrderLineMapper;
import com.example.ordermanagement.mappers.OrderMapper;
import com.example.ordermanagement.repositories.OrderLineRepository;
import com.example.ordermanagement.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderLineRepository orderLineRepository;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderLineMapper orderLineMapper;

    @Resource
    private ProductService productService;

    @Resource
    private CustomerService customerService;

    // ORDERS - CREATE, GET, UPDATE, DELETE

    public Order createOrder(OrderCreate orderCreate) {
        OrderEntity orderEntity = orderMapper.orderCreateToOrderEntity(orderCreate);
        orderRepository.save(orderEntity);
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineCreate orderLineCreate : orderCreate.getCreatedOrderLines()) {
            orderLines.add(createOrderLine(orderEntity.getId(),orderLineCreate));
        }
        Order order = orderMapper.orderEntityToOrder(orderEntity);
        order.setOrderLines(orderLines);
        return order;
    }

    public Order getOrder(Integer orderId) {
        Order order = orderMapper.orderEntityToOrder(getOrderEntity(orderId));
        order.setOrderLines(getOrderLinesByOrderId(orderId));
        return order;
    }

    public OrderEntity getOrderEntity(Integer orderId) {
        return orderRepository.findById(orderId).get();
    }

    public Order updateOrder(OrderUpdate orderUpdate) {
        OrderEntity orderEntity = getOrderEntity(orderUpdate.getOrderId());
        orderEntity.setCustomerEntity(customerService.getCustomerEntity(orderUpdate.getCustomerId()));
        orderEntity.setDate(orderUpdate.getDate());
        orderRepository.save(orderEntity);
        Order order = orderMapper.orderEntityToOrder(orderEntity);
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineUpdate orderLineUpdate : orderUpdate.getUpdatedOrderLines()) {
            orderLines.add(updateOrderLine(orderLineUpdate));
        }
        order.setOrderLines(orderLines);
        return order;
    }

    public void deleteOrder(Integer orderId){
        List<OrderLineEntity> orderLineEntities = orderLineRepository.getOrderLinesByOrderId(orderId);
        for (OrderLineEntity orderLineEntity : orderLineEntities){
           orderLineRepository.delete(orderLineEntity);
        }
        orderRepository.deleteById(orderId);
    }

    public OrderLine createOrderLine(Integer orderId, OrderLineCreate orderLineCreate) {
        OrderLineEntity orderLineEntity = orderLineMapper.orderLineCreateToOrderLineEntity(orderLineCreate);
        orderLineEntity.setOrderEntity(getOrderEntity(orderId));
        orderLineEntity.setProductEntity(productService.getProductEntity(orderLineCreate.getProductId()));
        orderLineRepository.save(orderLineEntity);
        return orderLineMapper.orderLineEntityToOrderLine(orderLineEntity);
    }

    public OrderLine updateOrderLine(OrderLineUpdate orderLineUpdate) {
        OrderLineEntity orderLineEntity = orderLineRepository.findById(orderLineUpdate.getOrderLineId()).get();
        orderLineEntity.setProductEntity(productService.getProductEntity(orderLineUpdate.getProductId()));
        orderLineEntity.setQuantity(orderLineUpdate.getQuantity());
        orderLineRepository.save(orderLineEntity);
        return orderLineMapper.orderLineEntityToOrderLine(orderLineEntity);

    }

    public void deleteOrderLine(Integer orderLineId) throws Exception {
        OrderEntity orderEntity = orderLineRepository.findById(orderLineId).get().getOrderEntity();
        List<OrderLineEntity> orderLineEntities = orderLineRepository.getOrderLinesByOrderId(orderEntity.getId());
        if (orderLineEntities.size() <= 1){
           throw new Exception("Order has to contain at least one order line");
        } else {
            orderLineRepository.deleteById(orderLineId);
        }
    }

    public List<Order> findOrdersByCustomerId(Integer customerId) {
        List<OrderEntity> orderEntities = orderRepository.findOrdersByCustomerId(customerId);
        List<Order> orders = orderMapper.orderEntitiesToOrders(orderEntities);
        for (Order order : orders){
            order.setOrderLines(getOrderLinesByOrderId(order.getId()));
        }
        return orders;
    }

    public List<Order> findOrdersByProductId(Integer productId) {
        List<Order> orders = new ArrayList<>();
        List<OrderLineEntity> orderLineEntities = orderLineRepository.findOrderLinesByProductId(productId);
        for (OrderLineEntity orderLineEntity : orderLineEntities){
            orders.add(getOrder(orderLineEntity.getOrderEntity().getId()));
            }
        return orders;
    }

    public List<Order> findOrdersByDate(LocalDate date){
        List<OrderEntity> orderEntities = orderRepository.findOrdersByDate(date);
        List<Order> orders = orderMapper.orderEntitiesToOrders(orderEntities);
        for (Order order : orders){
            order.setOrderLines(getOrderLinesByOrderId(order.getId()));
        }
        return orders;
    }

    private List<OrderLine> getOrderLinesByOrderId (Integer orderId){
        List<OrderLineEntity> orderLineEntities = orderLineRepository.getOrderLinesByOrderId(orderId);
        return orderLineMapper.orderLineEntitiesToOrderLines(orderLineEntities);
    }
}
