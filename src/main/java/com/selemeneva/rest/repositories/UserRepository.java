package com.selemeneva.rest.repositories;

import com.selemeneva.rest.model.User;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    @Query("select u from User u join fetch u.roles where u.email=:email")
    Optional<User> findByUsername(String email);

    Optional<User> findById(Long id);

    void deleteById(Long id);
    void save(User user);
    Set<User> findAll();



}
