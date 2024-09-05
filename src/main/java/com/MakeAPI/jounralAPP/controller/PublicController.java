package com.MakeAPI.jounralAPP.controller;

import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @GetMapping("/health-check")
    public String heathCheck() {
        return "ok";

    }

}
