package com.selemeneva.rest.service;

import com.selemeneva.rest.model.Role;
import com.selemeneva.rest.model.User;
import com.selemeneva.rest.repositories.RoleRepository;
import com.selemeneva.rest.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Lazy
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void add(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Set<User> getAllUsers() {return userRepository.findAll();}

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Optional<User> getByUsername(String firstName) {
        return userRepository.findByUsername(firstName);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(User user, Long id) {
        User oldUser = getById(user.getId());
        if (oldUser.getPassword().equals(user.getPassword()) || "".equals(user.getPassword())) {
            user.setPassword(oldUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        Optional<User> userPrimary = getByUsername(firstName);
        if (userPrimary == null) {
            throw new UsernameNotFoundException(firstName + " not found");
        }
        return userPrimary.get();
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
