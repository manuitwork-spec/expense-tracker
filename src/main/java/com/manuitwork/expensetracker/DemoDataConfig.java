package com.manuitwork.expensetracker;

import com.manuitwork.expensetracker.group.GroupService;
import com.manuitwork.expensetracker.group.HouseholdGroupRepository;
import com.manuitwork.expensetracker.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoDataConfig {

    @Bean
    CommandLineRunner initGroups(UserRepository userRepository, GroupService groupService) {
        return args -> {
            userRepository.findAll().stream().findFirst().ifPresent(user -> {
                groupService.createGroup("Home Test", user);
            });
        };
    }
}
