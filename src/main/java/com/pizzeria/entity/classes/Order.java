package com.pizzeria.entity.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pizzeria.entity.enums.ORDER_STATUS;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Calendar;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    @Column
    public ORDER_STATUS status;
    @OneToMany(mappedBy = "lastOrder", fetch = FetchType.LAZY)
    private List<Pizza> items;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User client;
    @Column(nullable = false)
    private Calendar creationDate;
    @Column
    public String address;
}
