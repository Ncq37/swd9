package com.main.controller;

import com.main.model.AccountStatus;
import com.main.model.Role;
import com.main.model.UserAccount;
import com.main.service.UserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        model.addAttribute("users", userManagementService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/admin/users/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new UserAccount());
        model.addAttribute("isEdit", false);
        return "user-form";
    }

    @PostMapping("/admin/users/add")
    public String addUser(@ModelAttribute("user") UserAccount userAccount) {
        userManagementService.addUser(userAccount);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userManagementService.getUserById(id));
        model.addAttribute("isEdit", true);
        return "user-form";
    }

    @PostMapping("/admin/users/{id}/edit")
    public String editUser(@PathVariable Long id, @ModelAttribute("user") UserAccount userAccount) {
        userManagementService.updateUser(id, userAccount);
        return "redirect:/admin/users";
    }

    @ModelAttribute("roles")
    public Role[] roles() {
        return Role.values();
    }

    @ModelAttribute("statuses")
    public AccountStatus[] statuses() {
        return AccountStatus.values();
    }
}
