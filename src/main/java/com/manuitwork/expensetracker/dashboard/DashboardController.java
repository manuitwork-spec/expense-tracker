package com.manuitwork.expensetracker.dashboard;

import com.manuitwork.expensetracker.group.CreateGroupForm;
import com.manuitwork.expensetracker.group.GroupService;
import com.manuitwork.expensetracker.group.HouseholdGroup;
import com.manuitwork.expensetracker.user.CustomUserDetails;
import com.manuitwork.expensetracker.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final GroupService groupService;

    public DashboardController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal CustomUserDetails currentUser,
                            Model model) {

        List<HouseholdGroup> groups = groupService.findGroupsForUser(currentUser.getUser());

        model.addAttribute("groups", groups);
        model.addAttribute("createGroupForm", new CreateGroupForm());

        return "dashboard";
    }

    @PostMapping("/groups")
    public String createGroup(@AuthenticationPrincipal CustomUserDetails currentUser, @ModelAttribute("createGroupForm") CreateGroupForm createGroupForm) {
        groupService.createGroup(createGroupForm.getName(), currentUser.getUser());

        return "redirect:/dashboard";
    }

}
