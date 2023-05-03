package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.classes.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {
    ResponseEntity<User> addUser(User u);
    List<User> getAllUsers();
    User getUserByID(Long ID);
    void deleteUser(Long ID);
}
