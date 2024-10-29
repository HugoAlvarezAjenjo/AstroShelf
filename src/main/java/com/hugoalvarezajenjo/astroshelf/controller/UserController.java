package com.hugoalvarezajenjo.astroshelf.controller;

import com.hugoalvarezajenjo.astroshelf.model.User;
import com.hugoalvarezajenjo.astroshelf.service.LoanService;
import com.hugoalvarezajenjo.astroshelf.service.UserService;
import com.hugoalvarezajenjo.astroshelf.types.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final LoanService loanService;

    @GetMapping("/register")
    public String showRegistrationForm(final Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") final User user) {
        user.setRole(Role.COMMON_USER);
        this.userService.registerUser(user);
        return "user/login";
    }

    @GetMapping("/login")
    public String showLoginForm(final Model model) {
        return "user/login";
    }

    @GetMapping("/profile")
    public String showProfile(final Model model) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        this.userService.findUserByUsername(username).ifPresent(user -> {
            model.addAttribute("user", user);
        });
        return "user/profile";
    }

    @PostMapping("/user/delete")
    public String deleteAccount(RedirectAttributes redirectAttributes) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        this.userService.findUserByUsername(username).ifPresent(user -> {
            this.userService.deleteUserById(user.getId());
        });
        return "redirect:/login?logout";
    }
}
