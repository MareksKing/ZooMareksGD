package com.zoo.mareks.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Enclosure {

    @Column(name = "enclosureId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int enclosureId;

    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Animal animal;

    @Column(name = "enclosureName")
    private String enclosureName;
    
    
    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;
    
    public Enclosure(Animal animal) {
        this.animal = animal;
        addEnclosureName(animal);
    }

    public Enclosure(Animal animal, Zoo zoo) {
        this.animal = animal;
        addEnclosureName(animal);
        this.zoo = zoo;
    }

    public Enclosure(Zoo zoo) {
        this.zoo = zoo;
        this.enclosureName = "Empty enclosure";
    }

    public void addEnclosureName(Animal animal){
        this.enclosureName = animal.getTitle() + " Enclosure";
    }

    public void addAnimal(Animal animal) {
        this.animal = animal;
    }

    public void removeAnimal(Animal animal) {
        this.animal = null;
    }
    


}
