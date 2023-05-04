package com.pizzeria.services;

import com.pizzeria.entity.classes.Cart;
import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.interfaces.PizzaInterface;
import com.pizzeria.repositories.PizzaRepository;
import com.pizzeria.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PizzaService implements PizzaInterface {
    @Autowired
    private final PizzaRepository pizzaRepository;

    @Autowired
    private final CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addPizza(Pizza p) {
        pizzaRepository.save(p);
    }

    @Transactional
    public void addPizzaToCart(Long pizzaID, String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        User user = userRepository.findById(userID).get();
        Cart userCart = user.getCart();
        Pizza p = pizzaRepository.findById(pizzaID).get();
        p.setCarts(userCart);
        if (userCart == null) {
            userCart = new Cart();
            userCart.setItems(new ArrayList<Pizza>());
            userCart.setPrice(0);
        }
        pizzaRepository.save(p);
        cartService.addPizzaPrice(pizzaID, userID);
        pizzaRepository.save(p);
        userRepository.save(user);
    }

    @Transactional
    public Pizza getPizzaByID(Long ID) {
        return pizzaRepository.findById(ID).orElseThrow(() ->
                new IllegalStateException("Pizza not found"));
    }

    @Transactional
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
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
