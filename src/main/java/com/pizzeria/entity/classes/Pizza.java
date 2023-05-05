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
    @Column
    public boolean show;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    public Cart carts;

    public String getRubPrice() {
        return getPrice() + " руб.";
    }
    @Override
    public String toString() {
        return "PIZZA ID: " + this.ID + "; NAME: " + this.name + "; PRICE: " + this.price + "; IS SHOW: " + this.show;
    }
}
