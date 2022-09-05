package com.example.apirestaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "deliveryAddress_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> itens;

    private final static String locale[] = {"pt", "BR"};

    public Double getTotal() {
        Double sum = 0.0;
        for (OrderItem oi : itens) {
            sum += oi.getSubTotal();
        }
        return sum;
    }


    @Override
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale(locale[0], locale[1]));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder();
        sb.append("Order number: ").append(getId()).append(", ");
        sb.append("Instant: ").append(sdf.format(getInstant())).append(", ");
        sb.append("Client: ").append(getClient().getName()).append(", ");
        sb.append("Payment Status: ").append(getPayment().getPaymentStatus().getDescription()).append("\n");
        sb.append("Detais: ").append("\n");
        for(OrderItem oi : getItens()) {
            sb.append(oi.toString());
        }
        sb.append("\n");
        sb.append("TOtal: ").append(nf.format(getTotal()));
        return sb.toString();
    }
}
