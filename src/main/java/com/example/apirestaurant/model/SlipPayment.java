package com.example.apirestaurant.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_slip_payment")
public class SlipPayment extends Payment{

    private Date dueDate;
    private Date payDate;
}
