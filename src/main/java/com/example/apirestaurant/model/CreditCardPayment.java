package com.example.apirestaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_credit_card_payment")
public class CreditCardPayment extends Payment {

    private Integer numberOfInstallemnts;
}
