package com.example.apirestaurant.model.enums;

import com.example.apirestaurant.service.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentStatusEnum {

    PENDING(1, "Pendente"),
    PAID(2, "Pago"),
    CANCELED(3, "Cancelado");

    private Integer id;
    private String description;

    public static PaymentStatusEnum toEnum(Integer id) {
        if(id == null)
            return null;
        for(PaymentStatusEnum ct : PaymentStatusEnum.values()) {
            if(id.equals(ct.getId()))
                return ct;
        }
        throw new BadRequestException("Invalid id: " + id);
    }
}
