package com.selemeneva.rest.controller;

import com.selemeneva.rest.model.User;
import com.selemeneva.rest.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("api/admin")
public class AdminRESTController {
    private final UserService userService;

    public AdminRESTController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<Set<User>> showUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        return new ResponseEntity<> (userService.getById(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> saveNewUser(
            @RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<> (HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> userSaveEdit(@RequestBody @NotNull User user, @PathVariable Long id) {
        user.setId(id);
        System.out.println(user);
        userService.update(user, id);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
