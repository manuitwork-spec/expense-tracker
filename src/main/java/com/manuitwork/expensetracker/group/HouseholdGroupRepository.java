package com.manuitwork.expensetracker.group;

import com.manuitwork.expensetracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseholdGroupRepository extends JpaRepository<HouseholdGroup, Integer> {

    List<HouseholdGroup> findByMembersContaining(User user);
    boolean existsByNameIgnoreCaseAndMembersContaining(String name, User user);
}
