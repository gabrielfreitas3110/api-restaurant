package com.example.apirestaurant.service.validation;

import com.example.apirestaurant.controller.exception.FieldMessage;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.enums.ClientTypeEnum;
import com.example.apirestaurant.repository.ClientRepository;
import com.example.apirestaurant.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientRequestDto> {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClientRequestDto obj, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();
        if(clientRepository.findByCpfCnpj(obj.getCpfCnpj()) != null)
            errors.add(new FieldMessage("cpfCnpj", "CPF or CNPJ already exist!" ));
        if(clientRepository.findByEmail(obj.getEmail()) != null)
            errors.add(new FieldMessage("email", "Email already exists"));
        if(obj.getType().equals(ClientTypeEnum.NATURAL_PERSON.getId()) && !BR.isValidSsn(obj.getCpfCnpj()))
            errors.add(new FieldMessage("cpfCnpj", "Invalid CPF!"));
        if(obj.getType().equals(ClientTypeEnum.LEGAL_PERSON.getId()) && !BR.isValidTfn(obj.getCpfCnpj()))
            errors.add(new FieldMessage("cpfCnpj", "Invalid CNPJ!"));
        for(FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
