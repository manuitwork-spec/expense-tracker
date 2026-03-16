package com.manuitwork.expensetracker.config;

import com.manuitwork.expensetracker.user.User;
import com.manuitwork.expensetracker.user.UserRepository;
import com.manuitwork.expensetracker.user.UserRole;
import com.manuitwork.expensetracker.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner adminUserInitializer(UserService userService,
                                                  PasswordEncoder passwordEncoder) {
        return args -> {
            String adminEmail = "admin@expense.local";

            if (userService.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setFirstName("Admin");
                admin.setLastName("User");
                admin.setEnabled(true);
                admin.setRole(UserRole.ADMIN);
                admin.setPassword(passwordEncoder.encode("password"));

                userService.createUser(admin);
            }
        };
    }
}
