package com.example.apirestaurant.service.validation;

import com.example.apirestaurant.controller.exception.FieldMessage;
import com.example.apirestaurant.model.Client;
import com.example.apirestaurant.model.dto.request.ClientUpdateRequestDto;
import com.example.apirestaurant.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientUpdateRequestDto> {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void initialize(ClientUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClientUpdateRequestDto obj, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));
        List<FieldMessage> errors = new ArrayList<>();
        Client c = clientRepository.findByEmail(obj.getEmail());
        if(c != null && !c.getId().equals(uriId))
            errors.add(new FieldMessage("email", "Email already exists"));
        for(FieldMessage e : errors) {
            context.disableDefaultConstraintViolation();;
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return errors.isEmpty();
    }
}
