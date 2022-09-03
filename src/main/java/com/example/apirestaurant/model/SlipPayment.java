package com.example.apirestaurant.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_slip_payment")
public class SlipPayment extends Payment{

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date payDate;
}
