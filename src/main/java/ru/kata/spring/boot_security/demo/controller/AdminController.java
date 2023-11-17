package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.Persistence;
import java.security.Principal;

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
    @GetMapping("/user")
    public String pageUser(Model model,@RequestParam(value = "nameId", required = false) Long id) {
        model.addAttribute("user",userService.showUser(id));
        return "/user";
    }
    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user",new User());
        return "/newUser";
    }
    @PostMapping
    public String saveUser(@ModelAttribute("user") User user
            ,@RequestParam(value = "roles") String[] roles
            ,@RequestParam(value = "name") String name
            ,@RequestParam(value = "surname") String surname
            ,@RequestParam(value = "id") Long id) {
        user.setRoles(roleService.getSetOfRoles(roles));
        user.setName(name);
        user.setSurname(surname);
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/admin";
    }
    @GetMapping("/delete")
    public String delUser(@RequestParam(value = "id") Long id) {
        userService.delUser(id);
        return "redirect:/admin";
    }
}
