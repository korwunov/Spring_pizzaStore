package com.pizzeria.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ROLES implements GrantedAuthority {
    ADMIN,
    EMPLOYEE,
    USER;

    @Override
    public String getAuthority() {
        return name().toString();
    }
}
