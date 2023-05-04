package com.pizzeria.controllers;

import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.NoSuchObjectException;
import java.util.List;

@Controller
@RequestMapping("/pizza")
@ResponseBody
public class PizzaController {
    @Autowired
    public PizzaService pService;

    @PostMapping("/action")
    public HttpStatus addPizza(@RequestBody Pizza p) {
        try {
            pService.addPizza(p);
            return HttpStatus.CREATED;
        }
        catch(Exception e) {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping("/action")
    public HttpStatus deletePizza(@RequestBody Long ID) {
        try {
            pService.deletePizza(ID);
            return HttpStatus.OK;
        }
        catch (NoSuchObjectException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("")
    public List<Pizza> getAllPizzas(Model model) {
        return pService.getAllPizzas();
    }

    @PostMapping("/addPizzaToCart/{pizzaID}")
    public void addPizzaToCart(@PathVariable Long pizzaID) {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        pService.addPizzaToCart(pizzaID, user);
    }

}
