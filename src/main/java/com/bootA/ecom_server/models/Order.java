 package com.bootA.ecom_server.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bootA.ecom_server.enums.OrderStatus;

import java.util.*;
import java.util.Objects;

@Document(collection = "orders")
public class Order{

    @Id
    private String id;

    // reference to the user who placed the order
    private String userId;

    // key = productId, value = quantity
    private Map<String, Integer> products;

    private OrderStatus status;
    private Double totalAmount;

    // Use java.util.Date for best compatibility with MongoDB
    private Date createdAt = new Date();

    // No-arg constructor
    public Order() { }

    // All-args constructor
    public Order(String id, String userId, Map<String, Integer> products,
                 OrderStatus status, Double totalAmount, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.status = status;
        this.totalAmount = totalAmount;
        this.createdAt = (createdAt == null ? new Date() : createdAt);
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public Map<String, Integer> getProducts() { return products; }
    public void setProducts(Map<String, Integer> products) { this.products = products; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", products=" + products +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    

}
