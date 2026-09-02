package com.manuitwork.expensetracker.group;

import com.manuitwork.expensetracker.user.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final HouseholdGroupRepository householdGroupRepository;

    public GroupService(HouseholdGroupRepository householdGroupRepository) {
        this.householdGroupRepository = householdGroupRepository;
    }

    @Transactional
    public HouseholdGroup createGroup(String name, User owner){
        String cleanName = name != null ? name.trim() : "";
        if (cleanName.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty");
        }

        if (householdGroupRepository.existsByNameIgnoreCaseAndMembersContaining(name, owner)){
            throw new DuplicateGroupNameException("There is already a group with this name: " + name);
        }

        HouseholdGroup group = new HouseholdGroup();
        group.setName(name);
        group.setOwner(owner);
        group.getMembers().add(owner);

        return householdGroupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public List<HouseholdGroup> findAll() {
        return householdGroupRepository.findAll();
    }

    public List<HouseholdGroup> findGroupsForUser(User user) {
        return householdGroupRepository.findByMembersContaining(user);
    }
}
