package com.pizzeria.entity.classes;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.annotation.Nullable;
//import jakarta.persistence.*;
//import lombok.Data;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

@Data
public class Address {
    private String address;
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long ID;
//    @Column(nullable = false)
//    private String country;
//    @Column(nullable = false)
//    private String region;
//    @Column(nullable = false)
//    private String city;
//    @Column(nullable = false)
//    private String street;
//    @Column(nullable = false)
//    private int house;
//    @Column
//    @Nullable
//    private String comment;
//    @ManyToOne
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private User owner;
//    public String toString() {
//        return ID.toString() + ": " + country + " " + region + " " + city
//                + " " + street + " " + "; OWNER ID: " +  owner.getID().toString();
//    }
}
