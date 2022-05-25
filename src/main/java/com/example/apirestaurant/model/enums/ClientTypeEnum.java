package com.example.apirestaurant.model.enums;

import com.example.apirestaurant.model.exception.IllegalAccessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientTypeEnum {

    NATURAL_PERSON(1, "Pessoa Física"),
    LEGAL_PERSON(2, "Pessoa Jurídica");

    private Integer cod;
    private String description;

    public static ClientTypeEnum toEnum(Integer cod) {
        if(cod == null)
            return null;
        for(ClientTypeEnum ct : ClientTypeEnum.values()) {
            if(cod.equals(ct.getCod()))
                return ct;
        }
        throw new IllegalAccessException("Invalid id: " + cod);
    }
}
