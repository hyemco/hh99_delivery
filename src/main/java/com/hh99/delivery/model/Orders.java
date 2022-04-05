package com.hh99.delivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable= false)
    private Restaurant restaurant;

    public Orders(Restaurant restaurant){
        this.restaurant = restaurant;
    }
}