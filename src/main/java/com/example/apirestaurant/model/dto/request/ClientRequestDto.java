package com.example.apirestaurant.model.dto.request;

import com.example.apirestaurant.model.enums.ClientTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientRequestDto {

    private String name;
    private String cpfOrCnpj;
    private ClientTypeEnum type;
}
