package com.example.demo;

import com.example.demo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// Просто наследуемся от JpaRepository и всё - он уже умеет делать CRUD операции
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
