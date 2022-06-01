package com.example.apirestaurant.model.enums;

import com.example.apirestaurant.service.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientTypeEnum {

    NATURAL_PERSON(1, "Pessoa Física"),
    LEGAL_PERSON(2, "Pessoa Jurídica");

    private Integer id;
    private String description;

    public static ClientTypeEnum toEnum(Integer id) {
        if(id == null)
            return null;
        for(ClientTypeEnum ct : ClientTypeEnum.values()) {
            if(id.equals(ct.getId()))
                return ct;
        }
        throw new BadRequestException("Invalid id: " + id);
    }
}
