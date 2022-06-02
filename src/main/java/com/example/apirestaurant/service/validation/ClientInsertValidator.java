package com.example.apirestaurant.service.validation;

import com.example.apirestaurant.controller.exception.FieldMessage;
import com.example.apirestaurant.model.dto.request.ClientRequestDto;
import com.example.apirestaurant.model.enums.ClientTypeEnum;
import com.example.apirestaurant.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientRequestDto> {

    @Override
    public void initialize(ClientInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClientRequestDto obj, ConstraintValidatorContext context) {
        List<FieldMessage> errors = new ArrayList<>();
        if(obj.getType().equals(ClientTypeEnum.NATURAL_PERSON.getId()) && !BR.isValidSsn(obj.getCpfOrCnpj()))
            errors.add(new FieldMessage("cpfOrCnpj", "Invalid CPF!"));
        if(obj.getType().equals(ClientTypeEnum.LEGAL_PERSON.getId()) && !BR.isValidTfn(obj.getCpfOrCnpj()))
            errors.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ!"));
        for(FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
