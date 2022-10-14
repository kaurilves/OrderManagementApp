package com.example.ordermanagement.services;

import com.example.ordermanagement.dtos.order.Order;
import com.example.ordermanagement.dtos.order.OrderCreate;
import com.example.ordermanagement.dtos.order.OrderUpdate;
import com.example.ordermanagement.dtos.order.orderline.OrderLine;
import com.example.ordermanagement.dtos.order.orderline.OrderLineCreate;
import com.example.ordermanagement.dtos.order.orderline.OrderLineUpdate;
import com.example.ordermanagement.entities.OrderEntity;
import com.example.ordermanagement.entities.OrderLineEntity;
import com.example.ordermanagement.mappers.CustomerMapper;
import com.example.ordermanagement.mappers.OrderLineMapper;
import com.example.ordermanagement.mappers.OrderMapper;
import com.example.ordermanagement.mappers.ProductMapper;
import com.example.ordermanagement.repositories.OrderLineRepository;
import com.example.ordermanagement.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Resource
    OrderRepository orderRepository;

    @Resource
    OrderLineRepository orderLineRepository;

    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderLineMapper orderLineMapper;

    @Resource
    ProductService productService;

    @Resource
    CustomerService customerService;

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
        order.setOrderLines(orderLineMapper.orderLineEntitiesToOrderLines(orderLineRepository.getOrderLinesByOrderId(orderId)));
        for (OrderLine orderLine : order.getOrderLines()){
            addInfoToOrderLine(orderLine);
        }
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
    // ORDERLINES - CREATE, UPDATE, DELETE

    public OrderLine createOrderLine(Integer orderId, OrderLineCreate orderLineCreate) {
        OrderLineEntity orderLineEntity = orderLineMapper.orderLineCreateToOrderLineEntity(orderLineCreate);
        orderLineEntity.setOrderEntity(getOrderEntity(orderId));
        orderLineEntity.setProductEntity(productService.getProductEntity(orderLineCreate.getProductId()));
        orderLineRepository.save(orderLineEntity);
        OrderLine orderLine = orderLineMapper.orderLineEntityToOrderLine(orderLineEntity);
        addInfoToOrderLine(orderLine);
        return orderLine;
    }

    public OrderLine updateOrderLine(OrderLineUpdate orderLineUpdate) {
        OrderLineEntity orderLineEntity = orderLineRepository.findById(orderLineUpdate.getOrderLineId()).get();
        orderLineEntity.setProductEntity(productService.getProductEntity(orderLineUpdate.getProductId()));
        orderLineEntity.setQuantity(orderLineUpdate.getQuantity());
        orderLineRepository.save(orderLineEntity);
        OrderLine orderLine = orderLineMapper.orderLineEntityToOrderLine(orderLineEntity);
        addInfoToOrderLine(orderLine);
        return orderLine;
    }

    // adds name, skucode, unitprice and totalprice to ordeline to display them with order.
    private void addInfoToOrderLine(OrderLine orderLine) {
        OrderLineEntity orderLineEntity = orderLineRepository.findById(orderLine.getOrderLineId()).get();
        orderLine.setProductName(orderLineEntity.getProductEntity().getName());
        orderLine.setSkuCode(orderLineEntity.getProductEntity().getSkuCode());
        orderLine.setUnitPrice(orderLineEntity.getProductEntity().getPrice());
        orderLine.setOrderLineSum(calculateOrderLineSum(orderLine.getQuantity(), orderLine.getUnitPrice()));
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

    public BigDecimal calculateOrderLineSum(Integer quantity, BigDecimal unitPrice) {
        BigDecimal totalCost = BigDecimal.ZERO;
        totalCost = unitPrice.multiply(BigDecimal.valueOf(quantity));
        return totalCost;
    }

    public List<Order> findOrdersByCustomerId(Integer customerId) {
        List<OrderEntity> orderEntities = orderRepository.findOrdersByCustomerId(customerId);
        List<Order> orders = orderMapper.orderEntitiesToOrders(orderEntities);
        for (Order order : orders) {
            for (OrderLine orderLine : order.getOrderLines()){
                addInfoToOrderLine(orderLine);
            }
        }
        return orders;
    }

    public List<Order> findOrdersByProductId(Integer productId) {
        List<OrderEntity> orderEntities = new ArrayList<>();
        List<OrderLineEntity> orderLineEntities = orderLineRepository.findOrderLinesByProductId(productId);
        for (OrderLineEntity orderLineEnitity : orderLineEntities){
            orderEntities.add(orderLineEnitity.getOrderEntity());
            }
        List<Order> orders = orderMapper.orderEntitiesToOrders(orderEntities);
        for (Order order : orders) {
            for (OrderLine orderLine : order.getOrderLines()){
                addInfoToOrderLine(orderLine);
            }
        }
        return orders;
    }
}
