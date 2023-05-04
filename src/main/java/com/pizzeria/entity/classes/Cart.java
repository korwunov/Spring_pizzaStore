package com.pizzeria.entity.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzeria.entity.interfaces.CartInterface;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.processing.Generated;
import java.util.List;

@Entity
@Data
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @OneToMany(mappedBy = "carts")
    private List<Pizza> items;
    @Column
    public int price;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User cartClient;
}
