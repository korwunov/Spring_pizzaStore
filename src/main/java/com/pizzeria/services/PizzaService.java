package com.pizzeria.services;

import com.pizzeria.entity.classes.Cart;
import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.interfaces.PizzaInterface;
import com.pizzeria.repositories.CartRepository;
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
    private final CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void addPizza(Pizza p) {
        p.setShow(true);
        pizzaRepository.save(p);
    }

    @Transactional
    public void addPizzaToCart(Long pizzaID, String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        User user = userRepository.findById(userID).get();
        Cart userCart = user.getCart();
        Pizza p = pizzaRepository.findById(pizzaID).get();
        if (userCart == null) {
            userCart = new Cart();
            userCart.setItems(new ArrayList<Pizza>());
            userCart.setPrice(0);
            userCart.setCartClient(user);
            cartRepository.save(userCart);
        }
        Pizza newPizza = new Pizza();
        newPizza.setName(p.getName());
        newPizza.setPrice(p.getPrice());
        newPizza.setShow(false);
        newPizza.setPathToImage(p.getPathToImage());
        userCart.items.add(newPizza);
        newPizza.setCarts(userCart);
        pizzaRepository.save(newPizza);
        cartRepository.save(userCart);
        userRepository.save(user);
        cartService.addPizzaPrice(pizzaID, userID);
    }

    @Transactional
    public void removePizzaFromCart(Long pizzaID, String userName) throws Exception {
        Long userID = userRepository.findByEmail(userName).get().getID();
        User user = userRepository.findById(userID).get();
        Cart cart = user.getCart();
        Pizza pizza = pizzaRepository.findById(pizzaID).get();
        if (cart != null && cart.items.size() > 0) {
            cart.items.remove(pizza);
            pizza.carts.items.remove(pizza);
            cartService.removePizzaPrice(pizzaID, userID);
            pizzaRepository.deleteById(pizzaID);
            cartRepository.save(cart);
        }
        else {
            throw new Exception("Корзина не существует или пуста");
        }
    }
    @Transactional
    public void cleanCart(String userName) {
        Long userID = userRepository.findByEmail(userName).get().getID();
        User user = userRepository.findById(userID).get();
        Cart cart = user.getCart();
        if (cart != null && cart.items.size() > 0) {
            for (Pizza p : cart.items) {
                pizzaRepository.deleteById(p.getID());
            }
            cart.setPrice(0);
            cart.items.clear();
            cartRepository.save(cart);
        }
    }

    @Transactional
    public Pizza getPizzaByID(Long ID) {
        return pizzaRepository.findById(ID).orElseThrow(() ->
                new IllegalStateException("Pizza not found"));
    }

    @Transactional
    public List<Pizza> getAllPizzas() {
        List<Pizza> list = pizzaRepository.findAll();
        //ArrayList<Pizza> array = (ArrayList<Pizza>) list;
        int num = list.size();
        int i = 0;
        while (i < num) {
            Pizza p = list.get(i);
            if (!(p.isShow())) {
                list.remove(p);
                num--;
            }
            else { i++; }
        }
        return list;
    }

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
