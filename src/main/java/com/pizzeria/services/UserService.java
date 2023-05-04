package com.pizzeria.services;

import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.interfaces.UserInterface;
import com.pizzeria.repositories.PizzaRepository;
import com.pizzeria.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserInterface {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PizzaRepository pizzaRepository;
    public ResponseEntity<User> addUser(User u) {
        User newUser = userRepository.save(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByID(Long ID) {
        return userRepository.findById(ID).orElseThrow(() ->
                new IllegalStateException("User not found"));
    }
    @Transactional
    public void deleteUser(Long ID) {
        userRepository.delete(userRepository.findById(ID).get());
    }

    @Transactional
    public void addPizzaToCart(Pizza p) {
        pizzaRepository.findById(p.getID());
    }

    @Transactional
    public List<Pizza> getUsersCartContains(String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        return userRepository.findById(userID).get().getCart().getItems();
    }

    @Transactional
    public int getUserCartPrice(String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        return userRepository.findById(userID).get().getCart().getPrice();
    }

}
