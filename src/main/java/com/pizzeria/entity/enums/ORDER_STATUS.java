package com.pizzeria.entity.enums;

public enum ORDER_STATUS {
    CREATED,
    SENT_TO_KITCHEN,
    COOKED,
    SENT_TO_DELIVERY,
    DELIVERED_TO_CLIENT,
    CLOSED;

    public ORDER_STATUS getStatusBySTR(String inStr) {
        ORDER_STATUS targetStatus = CREATED;
        switch (inStr) {
            case "CREATED" -> { targetStatus = CREATED; }
            case "SENT_TO_KITCHEN" -> { targetStatus = SENT_TO_KITCHEN; }
            case "COOKED" -> { targetStatus = COOKED; }
            case "SENT_TO_DELIVERY" -> { targetStatus = SENT_TO_DELIVERY; }
            case "DELIVERED_TO_CLIENT" -> { targetStatus = DELIVERED_TO_CLIENT; }
            case "CLOSED" -> { targetStatus = CLOSED; }
        }
        return targetStatus;
    }
}
