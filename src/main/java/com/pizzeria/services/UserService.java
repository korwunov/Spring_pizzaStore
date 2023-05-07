package com.pizzeria.services;

import com.pizzeria.entity.classes.Cart;
import com.pizzeria.entity.classes.Order;
import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.enums.ROLES;
import com.pizzeria.entity.interfaces.UserInterface;
import com.pizzeria.repositories.CartRepository;
import com.pizzeria.repositories.OrderRepository;
import com.pizzeria.repositories.PizzaRepository;
import com.pizzeria.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserInterface {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PizzaRepository pizzaRepository;
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final CartRepository cartRepository;
    @Autowired
    private final MailService mailService;
    public void addUser(User u) {

        userRepository.save(u);
        mailService.sendRegistrationEmail(u);
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
    public List<Order> getAllOrders(String userName) {
        User user = userRepository.findByEmail(userName).get();
        if (user.getRole().contains(ROLES.ROLE_EMPLOYEE) ||
            user.getRole().contains(ROLES.ROLE_ADMIN))
        {
            return orderRepository.findAll();
        }
        else {
            return user.getOrders();
        }

    }

    @Transactional
    public List<Pizza> getUsersCartContains(String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        User user = userRepository.findById(userID).get();
        Cart userCart = user.getCart();
        if (userCart == null) {
            Cart newCart = new Cart();
            newCart.setPrice(0);
            newCart.setCartClient(user);
            newCart.setItems(new ArrayList<Pizza>());
            cartRepository.save(newCart);
            user.setCart(newCart);
        }
        return user.getCart().getItems();
    }

    @Transactional
    public int getUserCartPrice(String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        return userRepository.findById(userID).get().getCart().getPrice();
    }

    public void setAdminRole(Long userID) {
        User u = userRepository.findById(userID).get();
        Set<ROLES> roles = u.getRole();
        roles.clear();
        roles.add(ROLES.ROLE_ADMIN);
        u.setRole(roles);
        userRepository.save(u);
    }

    public void setEmployeeRole(Long userID) {
        User u = userRepository.findById(userID).get();
        u.setRole(Collections.singleton(ROLES.ROLE_EMPLOYEE));
        userRepository.save(u);
    }

}
