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
    public int price;
    @Column(nullable = false)
    public String pathToImage;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Order lastOrder;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Cart carts;

    public String getRubPrice() {
        return getPrice() + " руб.";
    }
}
