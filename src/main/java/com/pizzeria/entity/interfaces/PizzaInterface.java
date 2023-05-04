package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.classes.Pizza;

import java.rmi.NoSuchObjectException;
import java.util.List;

public interface PizzaInterface {
    void addPizza(Pizza p);
    void deletePizza(Long ID) throws NoSuchObjectException;
    List<Pizza> getAllPizzas();
    Pizza getPizzaByID(Long ID);
    //Pizza getPizzaByName(String name);
}
