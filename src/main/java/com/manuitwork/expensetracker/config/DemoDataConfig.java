package com.manuitwork.expensetracker.config;

import com.manuitwork.expensetracker.group.GroupService;
import com.manuitwork.expensetracker.user.User;
import com.manuitwork.expensetracker.user.UserRepository;
import com.manuitwork.expensetracker.user.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoDataConfig {

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    CommandLineRunner initDemoGroups(UserRepository userRepository,
                                     GroupService groupService,
                                     PasswordEncoder  passwordEncoder) {
        return args -> {
            User admin = userRepository.findByEmail(adminEmail)
                    .orElseGet(() -> {
                        User u = new User();
                        u.setEmail(adminEmail);
                        u.setFirstName("Admin");
                        u.setLastName("User");
                        u.setPassword(passwordEncoder.encode(adminPassword));
                        u.setEnabled(true);
                        u.setRole(UserRole.ADMIN);
                        return userRepository.save(u);
                    });

            if (groupService.findGroupsForUser(admin).isEmpty()) {
                groupService.createGroup("Admin House", admin);
            } else {
                System.out.println("[DemoDataConfig] - Groups already exist for admin");
            }
        };
    }
}