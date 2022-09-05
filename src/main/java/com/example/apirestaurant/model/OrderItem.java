package com.example.apirestaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {

    @JsonIgnore
    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();

    private Double discount;
    private Integer quantity;
    private Double price;
    private final static String locale[] = {"pt", "BR"};

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getSubTotal() {
        return (price - discount) * quantity;
    }

    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        this.id.setOrder(order);
    }

    @JsonIgnore
    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public OrderItemPk getId() {
        return id;
    }

    public void setId(OrderItemPk id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale(locale[0], locale[1]));
        final StringBuilder sb = new StringBuilder();
        sb.append(getProduct().getName()).append(", ");
        sb.append("Qte: ").append(getQuantity()).append(", ");
        sb.append("Unit price: ").append(nf.format(getPrice())).append(", ");
        sb.append("Subtotal: ").append(nf.format(getSubTotal())).append("\n");
        return sb.toString();
    }
}
