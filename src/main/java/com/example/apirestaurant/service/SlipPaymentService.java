package com.example.apirestaurant.service;

import com.example.apirestaurant.model.SlipPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SlipPaymentService {

    public void completeSlipPayment(SlipPayment slipPayment, Date moment) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(moment);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        slipPayment.setDueDate(calendar.getTime());
    }
}
