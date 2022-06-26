package com.zoo.mareks.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Zoo {

    // A primary key.
    @Column(name = "zooId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int zooId;

    // Creating a column in the database called zooName and it is a string.
    @Column(name = "zooName")
    private String name;

    // Creating a column in the database called zooTown and it is a Town.
    @Column(name = "zooTown")
    private Town town;

    // Creating a column in the database called zooPhone and it is a string.
    @Column(name = "zooPhone")
    private String phone;

    // Creating a column in the database called enclosures and it is a collection of
    // Enclosure.
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Enclosure> enclosures = new ArrayList<Enclosure>();

    // Creating a column in the database called animals and it is a collection of
    // Animal.
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Animal> animals = new ArrayList<Animal>();

    // Creating a column in the database called allFood and it is a collection of
    // FoodAnimal.
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<FoodAnimal> allFood = new ArrayList<FoodAnimal>();
    // Creating a column in the database called allTickets and it is a collection of
    // Ticket.

    @OneToMany(cascade = CascadeType.ALL)
    private Collection<Ticket> allTickets = new ArrayList<Ticket>();

    public Zoo(String name, Town town, String phone) {
        this.name = name;
        this.town = town;
        this.phone = phone;

    }

    public void addEnclosure(Enclosure enclosure) {
        this.enclosures.add(enclosure);
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public void removeEnclosure(Enclosure enclosure) {
        this.enclosures.remove(enclosure);
    }

    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    public void addFood(FoodAnimal food) {
        this.allFood.add(food);
    }

    public void removeFood(FoodAnimal foodAnimal) {
        this.allFood.remove(foodAnimal);
    }

    public void addTicket(Ticket ticket) {
        this.allTickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.allTickets.remove(ticket);
    }

}
