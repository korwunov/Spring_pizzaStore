package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserInterface {
    void addUser(User u);
    List<User> getAllUsers();
    User getUserByID(Long ID);
    void deleteUser(Long ID);
    void addPizzaToCart(Pizza p);
}
