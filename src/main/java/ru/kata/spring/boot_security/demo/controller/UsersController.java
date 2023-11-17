package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/user_role_user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String pageUser(Model model, Principal principal) {
        model.addAttribute("user",userService.findByUsername(principal.getName()));
        return "/user_role_user";
    }
}
