package com.zoo.mareks.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
@ToString
public class FoodAnimal {

    @Column(name = "foodId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int foodId;

    @Column(name = "foodType")
    private AnimalFoodType foodType;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "expiryDate")
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;
    public FoodAnimal(AnimalFoodType foodType, int quantity, Date deliveryDate, Date expiryDate) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.expiryDate = expiryDate;
    }

    public FoodAnimal(AnimalFoodType foodType, int quantity, String deliveryDate, String expiryDate) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.deliveryDate = Date.valueOf(deliveryDate);
        this.expiryDate = Date.valueOf(expiryDate);
    }

    

}
