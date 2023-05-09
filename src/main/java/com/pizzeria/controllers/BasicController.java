package com.pizzeria.controllers;

import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.services.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BasicController {
    @Autowired
    public PizzaService pService;
    @GetMapping("/")
    public String startPage(Model model) {
        List<Pizza> pizzas = pService.getAllPizzas();
        model.addAttribute("pizzas", pizzas);
        return "main";
    }

}
