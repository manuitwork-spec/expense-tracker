package com.manuitwork.expensetracker.group;

import com.manuitwork.expensetracker.user.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final HouseholdGroupRepository householdGroupRepository;

    public GroupService(HouseholdGroupRepository householdGroupRepository) {
        this.householdGroupRepository = householdGroupRepository;
    }

    @Transactional
    public HouseholdGroup createGroup(String name, User owner){
        HouseholdGroup group = new HouseholdGroup();
        group.setName(name);
        group.setOwner(owner);
        group.getMembers().add(owner);

        return householdGroupRepository.save(group);
    }
}
