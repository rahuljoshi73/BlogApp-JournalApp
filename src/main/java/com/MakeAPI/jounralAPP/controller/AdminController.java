package com.MakeAPI.jounralAPP.controller;

import com.MakeAPI.jounralAPP.entity.User;
import com.MakeAPI.jounralAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @GetMapping("/all-user")
    public ResponseEntity<?> getAll(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);

        }
        return new ResponseEntity<>(all,HttpStatus.NO_CONTENT);
    }
    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
