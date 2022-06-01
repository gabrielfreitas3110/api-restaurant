package com.example.apirestaurant.model.dto.request;

import com.example.apirestaurant.model.enums.ClientTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientRequestDto {

    @NotBlank(message = "Required field")
    @Size(min = 5, max = 50, message = "The size must be between 5 and 50 characters.")
    private String name;
    private String cpfOrCnpj;
    private ClientTypeEnum type;
}
