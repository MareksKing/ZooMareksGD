package com.zoo.mareks.models;

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
public class Animal {

    // Creating a column in the database called animalId, which is the primary key,
    // and it is auto
    // generated.
    @Column(name = "animalId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int animalId;

    // Creating a column in the database called animalName, which is the primary
    // key, and it is auto
    // generated.
    @Column(name = "animalName")
    private String title;

    // Creating a column in the database called animalFood.
    @Column(name = "animalFood")
    private AnimalFoodType food;

    // Creating a column in the database called animalCount.
    @Column(name = "animalCount")
    private int animalCount;

    // Creating a column in the database called enclosureId, which is the primary
    // key, and it is auto
    // generated.
    @ManyToOne
    @JoinColumn(name = "enclosureId")
    private Enclosure enclosure;

    // Creating a column in the database called zooId, which is the primary key, and
    // it is auto
    // generated.
    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;

    // A constructor.
    public Animal(String title, AnimalFoodType food, int animalCount) {
        this.title = title;
        this.food = food;
        this.animalCount = animalCount;
    }

    // A constructor.
    public Animal(String title, AnimalFoodType food, Enclosure enclosure, int animalCount) {
        this.title = title;
        this.food = food;
        addEnclosure(enclosure);
        this.animalCount = animalCount;
    }

    /**
     * This function adds an enclosure to the zoo
     * 
     * @param enclosure The enclosure object
     */
    public void addEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * This function removes an enclosure from a zoo.
     * 
     * @param enclosure The enclosure to be removed.
     */
    public void removeEnclosure(Enclosure enclosure) {
        this.enclosure = null;
    }

    /**
     * This function adds a zoo to the zoo list
     * 
     * @param zoo The Zoo object that the animal belongs to.
     */
    public void addZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     * This function removes the zoo from the zoo keeper.
     * 
     * @param zoo The Zoo object to be removed from the ZooKeeper object.
     */
    public void removeZoo(Zoo zoo) {
        this.zoo = null;
    }

}
