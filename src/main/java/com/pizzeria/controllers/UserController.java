package com.pizzeria.controllers;

import com.pizzeria.config.SecurityConfig;
import com.pizzeria.entity.classes.Order;
import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.enums.ROLES;
import com.pizzeria.services.PizzaService;
import com.pizzeria.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    public UserService service;

    @Autowired
    public PizzaService pizzaService;

    @PostMapping("/registration")
    public String addUser(@Validated @ModelAttribute("u") User u, Model model) {
        try {
            u.setPassword(SecurityConfig.passEncoder().encode(u.getPassword()));
            u.setRole(Collections.singleton(ROLES.ROLE_USER));
            u.setEnabled(true);
            service.addUser(u);
            return "redirect:/";
        }
        catch(Exception e) {
             model.addAttribute("errorMessage", "Что-то пoшло не так, попробуйте перезагрузить страницу");
             return "registration";
        }
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/get{ID}")
    public User getUserByID(@RequestParam Long ID) {
        return service.getUserByID(ID);
    }

    @DeleteMapping("/delete{ID}")
    @ResponseBody
    public void deleteUser(@RequestParam Long ID) throws Exception {
        try {
            service.deleteUser(ID);
        }
        catch(Exception e) {
            throw new Exception("Ошибка удаления пользователя");
        }
    }

    @GetMapping("/cart")
    public String getUserCart(Model model) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Pizza> p = service.getUsersCartContains(user);
        int price = service.getUserCartPrice(user);
        boolean orderEnable = price > 0;
        String priceSTR = String.valueOf(price) + " руб.";
        model.addAttribute("pizzasInCart", p);
        model.addAttribute("enableOrder", orderEnable);
        model.addAttribute("cartPrice", priceSTR);
        return "cart";
    }

    @GetMapping("/orders")
    public String ordersPage(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Order> orders = service.getAllOrders(userName);
        model.addAttribute("anyOrders", orders.size() > 0);
        model.addAttribute("allOrders", orders);
        return "orders";
    }

    @PostMapping("/setAdminRole/{userID}")
    public void setAdminRole(@PathVariable Long userID) {
        service.setAdminRole(userID);
    }

    @PostMapping("/setEmployeeRole/{userID}")
    public void setEmployeeRole(@PathVariable Long userID) {
        service.setEmployeeRole(userID);
    }

}
