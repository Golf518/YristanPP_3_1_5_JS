package ru.kata.spring.boot_security.demo.util;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UsersService;

import java.util.Collections;


@Component
public class Init implements ApplicationRunner {
    private final UsersService usersService;
    private final RoleRepository roleRepository;

    public Init(UsersService usersService, RoleRepository roleRepository) {
        this.usersService = usersService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User( "admin", "admin", 26, "admin@mail", "test");
        User user = new User( "user", "user", 22, "user@mail", "test");
        Role adminRole;
        Role userRole;
        roleRepository.save(adminRole = new Role("ROLE_ADMIN"));
        roleRepository.save(userRole = new Role("ROLE_USER"));
        admin.setRoles(Collections.singletonList(adminRole));
        user.setRoles(Collections.singletonList(userRole));
        usersService.saveUsers(admin);
        usersService.saveUsers(user);
    }
}