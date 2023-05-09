package com.pizzeria.services;

import com.pizzeria.entity.classes.Cart;
import com.pizzeria.entity.interfaces.CartInterface;
import com.pizzeria.repositories.CartRepository;
import com.pizzeria.repositories.PizzaRepository;
import com.pizzeria.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService implements CartInterface {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private UserRepository userRepository;

    public void addPizzaPrice(Long pizzaID, Long userID) {
        int price = pizzaRepository.findById(pizzaID).get().getPrice();
        Cart c = userRepository.findById(userID).get().getCart();
        if (c == null) {
            c = new Cart();
            c.setPrice(0);
        }
        c.price += price;
        cartRepository.save(c);
    }

    public void removePizzaPrice(Long pizzaID, Long userID) {
        int price = pizzaRepository.findById(pizzaID).get().getPrice();
        Cart c = userRepository.findById(userID).get().getCart();
        c.price -= price;
        cartRepository.save(c);
    }
}
