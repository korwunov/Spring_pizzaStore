package com.pizzeria.services;

import com.pizzeria.entity.classes.Order;
import com.pizzeria.entity.classes.Pizza;
import com.pizzeria.entity.classes.User;
import com.pizzeria.entity.enums.ORDER_STATUS;
import com.pizzeria.repositories.OrderRepository;
import com.pizzeria.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private MailService mailService;

    @Transactional
    public void createOrder(String userName, String address) {
        User user = userRepository.findByEmail(userName).get();

        List<Pizza> cartItems = user.getCart().items;
        Calendar dateNow = new GregorianCalendar();
        Order order = new Order();
        order.setAddress(address);
        order.setClient(user);
        order.setItemsInOrder(user.getCart());
        order.setPrice(user.getCart().getPrice());
        order.setStatus(ORDER_STATUS.CREATED);
        order.setCreationDate(dateNow);
        pizzaService.cleanCart(userName);
        orderRepository.save(order);
        //mailService.sendNewOrderEmail(user, order);

    }

    @Transactional
    public void setStatus(Long orderID, ORDER_STATUS status) {
        Order order = orderRepository.findById(orderID).get();
        order.setStatus(status);
        orderRepository.save(order);
        mailService.sendChangeOrderStatusEmail(order.getClient(), order);
    }

    @Transactional
    public void cancelOrder(Long orderID) {
        Order order = orderRepository.findById(orderID).get();
        order.setStatus(ORDER_STATUS.CLOSED);
        //mailService.sendCancelOrderEmail(order.getClient(), order);
        orderRepository.deleteById(orderID);
    }
}
