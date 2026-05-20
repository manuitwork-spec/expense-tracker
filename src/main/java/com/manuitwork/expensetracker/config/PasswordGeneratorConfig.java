package com.manuitwork.expensetracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordGeneratorConfig {

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    CommandLineRunner generateAdminPassword(PasswordEncoder passwordEncoder) {
        return args -> {
            String encoded = passwordEncoder.encode(adminPassword);
            System.out.println("BCrypt for " + adminPassword + ": " + encoded);
        };
    }
}
