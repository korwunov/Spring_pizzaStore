package com.pizzeria.repositories;

import com.pizzeria.entity.classes.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    //Pizza findByName(String name);
}
