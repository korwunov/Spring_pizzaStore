package com.pizzeria.controllers;

import com.pizzeria.config.SecurityConfig;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.enums.ROLES;
import com.pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    public UserService service;

    @PostMapping("/registration")
    @ResponseBody
    public HttpStatus addUser(@RequestBody User u) {
        try {
            u.setPassword(SecurityConfig.passEncoder().encode(u.getPassword()));
            u.setRole(Collections.singleton(ROLES.USER));
            u.setEnabled(true);
            service.addUser(u);
            return HttpStatus.CREATED;
        }
        catch(Exception e) {
             return HttpStatus.BAD_REQUEST;
        }
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "reg";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/get{ID}")
    public User getUserByID(@RequestParam Long ID) {
        return service.getUserByID(ID);
    }

    @DeleteMapping("/delete{id}")
    public HttpStatus deleteUser(@RequestParam Long ID) {
        try {
            service.deleteUser(ID);
            return HttpStatus.OK;
        }
        catch(Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

}
