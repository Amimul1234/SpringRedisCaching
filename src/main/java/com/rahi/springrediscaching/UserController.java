package com.rahi.springrediscaching;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController( UserService userService ) {
        this.userService = userService;
    }

    @Cacheable(value = "users")
    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
