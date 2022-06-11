package com.zoo.mareks.models;

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
    private AnimalFood food;

    @OneToOne(mappedBy = "animal")
    private Enclosure enclosure;

    @ManyToOne
    @JoinColumn(name = "zooName")
    private Zoo zoo;

    public Animal(String title, AnimalFood food) {
        this.title = title;
        this.food = food;
    }
    
}
