package com.example.apirestaurant.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("creditCardPayment")
@Entity
@Table(name = "tb_credit_card_payment")
public class CreditCardPayment extends Payment {

    private Integer numberOfInstallemnts;
}
