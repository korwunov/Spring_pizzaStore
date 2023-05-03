package com.pizzeria.services;

import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.interfaces.PizzaInterface;
import com.pizzeria.repositories.PizzaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PizzaService implements PizzaInterface {
    @Autowired
    private final PizzaRepository pizzaRepository;

    @Transactional
    public void addPizza(Pizza p) {
        pizzaRepository.save(p);
    }

    @Transactional
    public Pizza getPizzaByID(Long ID) {
        return pizzaRepository.findById(ID).orElseThrow(() ->
                new IllegalStateException("Pizza not found"));
    }

//    @Transactional
//    public Pizza getPizzaByName(String name) {
//        return pizzaRepository.findByName(name);
//    }

    @Transactional
    public void deletePizza(Long ID) throws NoSuchObjectException {
        try {
            pizzaRepository.deleteById(ID);
        }
        catch(Exception e) {
            throw new NoSuchObjectException("Pizza not found");
        }

    }
}
