package com.example.apirestaurant.model;

import com.example.apirestaurant.model.enums.PaymentStatusEnum;
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

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order order;

    public Payment(Long id, PaymentStatusEnum status, Order order) {
        this.id = id;
        this.paymentStatus = status.getId();
        this.order = order;
    }

    public PaymentStatusEnum setPaymentStatus() {
        return PaymentStatusEnum.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatusEnum status) {
        this.paymentStatus = status.getId();
    }
}
