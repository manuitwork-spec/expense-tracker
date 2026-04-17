package com.manuitwork.expensetracker.dashboard;

import com.manuitwork.expensetracker.user.CustomUserDetails;
import com.manuitwork.expensetracker.user.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String homeRedirect(){
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal CustomUserDetails currentUser, Model model){
        User user = currentUser.getUser();
        model.addAttribute("firstName", user.getFirstName());

        return "dashboard";
    }
}
