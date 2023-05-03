package com.pizzeria.entity.interfaces;

import com.pizzeria.entity.enums.ORDER_STATUS;
import com.pizzeria.entity.classes.Order;

public interface OrderInterface {
    void createOrder(Order o);
    void changeStatus(Long ID, ORDER_STATUS currentStatus);
    void getOrderByID(Long ID);
    void cancelOrder(Long ID);
}
