package com.example.apirestaurant.model;

import com.example.apirestaurant.model.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer paymentStatus;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public PaymentStatusEnum getPaymentStatus() {
        return PaymentStatusEnum.toEnum(paymentStatus);
    }
}
