package com.pizzeria.controllers;

import com.pizzeria.entity.classes.Address;
import com.pizzeria.entity.enums.ORDER_STATUS;
import com.pizzeria.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import static com.pizzeria.entity.enums.ORDER_STATUS.*;

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

//    @PostMapping("/changeOrderStatus/{status}/{orderID}/")
//    public String setOrderStatus(@PathVariable ORDER_STATUS status, Long orderID) {
//        oService.setStatus(orderID, status);
//        return "orders";
//    }

    @PostMapping("/changeOrderStatus{status}{orderID}")
    @ResponseBody
    public String setOrderStatus(@RequestParam String status, @RequestParam Long orderID) {
        ORDER_STATUS enumStatus = CREATED;
        switch (status) {
            case "CREATED" -> { enumStatus = CREATED; }
            case "SENT_TO_KITCHEN" -> { enumStatus = SENT_TO_KITCHEN; }
            case "COOKED" -> { enumStatus = COOKED; }
            case "SENT_TO_DELIVERY" -> { enumStatus = SENT_TO_DELIVERY; }
            case "DELIVERED_TO_CLIENT" -> { enumStatus = DELIVERED_TO_CLIENT; }
            case "CLOSED" -> { enumStatus = CLOSED; }
        }

        oService.setStatus(orderID, enumStatus);
        return enumStatus.toString() + " " + orderID.toString();
    }
}
