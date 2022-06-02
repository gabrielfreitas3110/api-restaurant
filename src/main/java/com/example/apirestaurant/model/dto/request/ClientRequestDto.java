package com.example.apirestaurant.model.dto.request;

import com.example.apirestaurant.model.enums.ClientTypeEnum;
import com.example.apirestaurant.service.validation.ClientInsert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ClientInsert
public class ClientRequestDto {

    @NotBlank(message = "Required field")
    @Size(min = 5, max = 50, message = "The size must be between 5 and 50 characters.")
    private String name;
    @NotBlank(message = "Required field")
    @Size(min = 5, max = 50, message = "The size must be between 5 and 50 characters.")
    @Email(message = "The Email Address is Not a Well Formed E-mail address")
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    public ClientTypeEnum getType() {
        return ClientTypeEnum.toEnum(type);
    }
    public void setType(ClientTypeEnum type) {
        this.type = type.getId();
    }
}
