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

    // Creating a column in the database called foodId, which is the primary key,
    // and it is auto generated.
    @Column(name = "foodId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private int foodId;

    // Creating a column in the database called foodType, which is a foreign key.
    @Column(name = "foodType")
    private AnimalFoodType foodType;

    // Creating a column in the database called quantity.
    @Column(name = "quantity")
    private int quantity;

    // Creating a column in the database called deliveryDate.
    @Column(name = "deliveryDate")
    private Date deliveryDate;

    // Creating a column in the database called expiryDate.
    @Column(name = "expiryDate")
    private Date expiryDate;

    // Creating a relationship between the FoodAnimal and Zoo classes.
    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;

    // A constructor.
    public FoodAnimal(AnimalFoodType foodType, int quantity, Date deliveryDate, Date expiryDate) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.deliveryDate = deliveryDate;
        this.expiryDate = expiryDate;
    }

    // A constructor.
    public FoodAnimal(AnimalFoodType foodType, int quantity, String deliveryDate, String expiryDate) {
        this.foodType = foodType;
        this.quantity = quantity;
        this.deliveryDate = Date.valueOf(deliveryDate);
        this.expiryDate = Date.valueOf(expiryDate);
    }

    /**
     * This function adds food to the zoo
     * 
     * @param zoo The Zoo object that the animal belongs to.
     */
    public void addZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     * This function removes the food from the zoo
     * 
     * @param zoo Zoo
     */
    public void removeZoo(Zoo zoo) {
        this.zoo = null;
    }

}
