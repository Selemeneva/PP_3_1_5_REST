package com.selemeneva.rest.util;

import com.selemeneva.rest.model.Role;
import com.selemeneva.rest.model.User;
import com.selemeneva.rest.service.RoleService;
import com.selemeneva.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class util {
    private UserService userService;
    private RoleService roleService;

    public util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");

        roleService.add(role1);
        roleService.add(role2);

        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        roleUser.add(role1);
        roleAdmin.add(role2);
        roleAdmin.add(role1);

        User user = new User("user", "user@mail.ru", "Jane", "Smith", (byte)30, roleUser);
        User admin = new User("admin", "admin@mail.ru", "John", "Smith", (byte)40, roleAdmin);

        userService.add(admin);
        userService.add(user);
    }
}
