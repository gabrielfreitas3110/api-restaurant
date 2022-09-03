package com.example.apirestaurant.comum;

import com.example.apirestaurant.model.CreditCardPayment;
import com.example.apirestaurant.model.SlipPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(CreditCardPayment.class);
                objectMapper.registerSubtypes(SlipPayment.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
