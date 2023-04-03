package com.selemeneva.rest.service;

import com.selemeneva.rest.model.Role;
import com.selemeneva.rest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {
    void add(User user);
    Set<User> getAllUsers();
    void delete(Long id);
    void update(User user, Long id);
    User getById(Long id);
    Optional<User> getByUsername(String userName);
    User getCurrentUser();
}
