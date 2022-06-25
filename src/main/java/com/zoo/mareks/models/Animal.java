package com.zoo.mareks.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @Column(name = "animalId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int animalId;

    @Column(name = "animalName")
    private String title;

    @Column(name = "animalFood")
    private AnimalFoodType food;

    @Column(name = "animalCount")
    private int animalCount;

    @ManyToOne
    @JoinColumn(name = "enclosureId")
    @ToString.Exclude
    private Enclosure enclosure;

    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;

    public Animal(String title, AnimalFoodType food, int animalCount) {
        this.title = title;
        this.food = food;
        this.animalCount = animalCount;
    }

    public Animal(String title, AnimalFoodType food, Enclosure enclosure, int animalCount) {
        this.title = title;
        this.food = food;
        addEnclosure(enclosure);
        this.animalCount = animalCount;
    }

    public void addEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public void removeEnclosure(Enclosure enclosure) {
        this.enclosure = null;
    }

    public void addZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public void removeZoo(Zoo zoo) {
        this.zoo = null;
    }
    
}
