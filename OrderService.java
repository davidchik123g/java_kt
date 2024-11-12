package com.example.demo;

import com.example.demo.Order;
import com.example.demo.OrderRepository;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Integer id, Order newOrder) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setProduct_name(newOrder.getProduct_name());
                    order.setQuantity(newOrder.getQuantity());
                    order.setPrice(newOrder.getPrice());
                    order.setStatus(newOrder.getStatus());
                    order.setOrder_date(newOrder.getOrder_date());
                    return orderRepository.save(order);
                })
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

}
