package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kata.spring.boot_security.demo.Service.RoleService;
import ru.kata.spring.boot_security.demo.Service.UserService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
		try {
			UserService userService = context.getBean(UserService.class);
			RoleService roleService = context.getBean(RoleService.class);

			User user1 = new User("admin", "admin", "admin");
			User user2 = new User("Pavel", "Pavlov", "Pavel");
			User user3 = new User("Ivan", "Ivanov","Ivan");

			Role roleAdmin = new Role("ROLE_ADMIN");
			Role roleUser = new Role("ROLE_USER");

			Set<Role> rolesAdmin = new HashSet<>();
			rolesAdmin.add(roleAdmin);
			rolesAdmin.add(roleUser);

			Set<Role> rolesUsers = new HashSet<>();
			rolesUsers.add(roleUser);

			user1.setRoles(rolesAdmin);
			user2.setRoles(rolesUsers);
			user3.setRoles(rolesUsers);

			roleService.addRole(roleAdmin);
			roleService.addRole(roleUser);

			userService.saveUser(user1);
			userService.saveUser(user2);
			userService.saveUser(user3);

		} catch (Exception ignored) {
		}
	}

}
