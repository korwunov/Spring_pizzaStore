package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.classes.Pizza;

import java.rmi.NoSuchObjectException;

public interface PizzaInterface {
    void addPizza(Pizza p);
    void deletePizza(Long ID) throws NoSuchObjectException;
    Pizza getPizzaByID(Long ID);
    //Pizza getPizzaByName(String name);
}
