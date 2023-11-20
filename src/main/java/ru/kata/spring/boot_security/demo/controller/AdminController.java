package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String pageAdmin(Model model) {
        model.addAttribute("people", userService.allUsers());
        return "/admin";
    }

    @GetMapping("/user_id")
    public String pageUser(Model model, @RequestParam(value = "nameId", required = false) Long id) {
        model.addAttribute("user", userService.showUser(id));
        return "user_id";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "/newUser";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user_id")
    public String saveUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String delUser(@RequestParam(value = "id") Long id) {
        userService.delUser(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getRoleByName(role.getRole()))
                .collect(Collectors.toSet()));
    }
}
