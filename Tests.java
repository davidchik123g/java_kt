package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(OrderController.class)
class Tests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getOrder_one() throws Exception {
        Order order = new Order(1, "PC", 1, BigDecimal.valueOf(799.99), Order.OrderStatus.CREATED, LocalDate.now());
        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    void getOrder_two() throws Exception {
        Order order = new Order(2, "type-c", 1, BigDecimal.valueOf(199.99), Order.OrderStatus.SHIPPED, LocalDate.now());
        when(orderService.getOrder(2)).thenReturn(order);

        mockMvc.perform(get("/orders/2"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrder_three() throws Exception {
        Order order = new Order(3, "phone", 1, BigDecimal.valueOf(499.99), Order.OrderStatus.DELIVERED, LocalDate.now());
        when(orderService.getOrder(3)).thenReturn(order);

        mockMvc.perform(get("/orders/3"))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_one() throws Exception {
        Order order = new Order(1, "PC", 1, BigDecimal.valueOf(799.99), Order.OrderStatus.CREATED, LocalDate.now());

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_two() throws Exception {
        Order order = new Order(2, "type-c", 1, BigDecimal.valueOf(199.99), Order.OrderStatus.SHIPPED, LocalDate.now());

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_three() throws Exception {
        Order order = new Order(3, "phone", 1, BigDecimal.valueOf(499.99), Order.OrderStatus.DELIVERED, LocalDate.now());

        mockMvc.perform(post("/orders")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk());
    }
}
