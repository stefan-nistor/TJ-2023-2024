package org.example.lab8.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.example.lab8.annotations.RegistrationNumber;

import java.util.UUID;

@ApplicationScoped
public class BeanConfig {
    @Produces
    @RegistrationNumber
    public String generateRegistrationNumber() {
        return UUID.randomUUID().toString();
    }
}
