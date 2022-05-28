package com.example.apirestaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @EmbeddedId
    private OrderIemPk id;

    private Double discount;
    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }
}
