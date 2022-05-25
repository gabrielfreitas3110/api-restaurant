package com.example.apirestaurant.model.dto.response;

import com.example.apirestaurant.model.Address;
import com.example.apirestaurant.model.enums.ClientTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientResponseDto {

    private Long id;
    private String name;
    private String cpfOrCnpj;
    private ClientTypeEnum type;
    private List<Address> addresses;
    private Set<String> cellphones;
}
