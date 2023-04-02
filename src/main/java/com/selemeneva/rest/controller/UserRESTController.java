package com.selemeneva.rest.controller;

import com.selemeneva.rest.model.User;
import com.selemeneva.rest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRESTController {
    private final UserService userService;

    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> showAuthUser() {
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }
}
