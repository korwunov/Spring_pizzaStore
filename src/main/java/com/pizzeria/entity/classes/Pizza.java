package com.pizzeria.entity.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Table(name = "pizzas")
public class Pizza{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column(nullable = false)
    public String name;
    @Column(nullable = false)
    public String ingredients;
    @Column(nullable = false)
    public int price;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Order lastOrder;
}
