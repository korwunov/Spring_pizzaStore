package com.pizzeria.controllers;

import com.pizzeria.entity.classes.Address;
import com.pizzeria.entity.enums.ORDER_STATUS;
import com.pizzeria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    public OrderService oService;

    @PostMapping("/createOrder")
    public String createOrder(@Validated @ModelAttribute("a") Address a) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        oService.createOrder(userName, a.getAddress());
        return "redirect:/user/orders";
    }

    @PostMapping("/cancelOrder/{orderID}")
    public String cancelOrder(@PathVariable Long orderID) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        oService.cancelOrder(orderID);
        return "orders";
    }

    @PostMapping("/changeOrderStatus/{status}/{orderID}/")
    public String setOrderStatus(@PathVariable ORDER_STATUS status, Long orderID) {
        oService.setStatus(orderID, status);
        return "orders";
    }
}
