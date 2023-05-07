package com.pizzeria.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum ROLES implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_EMPLOYEE,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name().toString();
    }
}
