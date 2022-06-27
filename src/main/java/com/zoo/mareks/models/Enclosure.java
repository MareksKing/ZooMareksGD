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
public class Enclosure {

    // Creating a column in the database called enclosureId, which is the primary
    // key, and it is auto
    // generated.
    @Column(name = "enclosureId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int enclosureId;

    // Creating a one to one relationship between the Enclosure and Animal class.
    @OneToOne(cascade = CascadeType.ALL)
    private Animal animal;

    // Creating a column in the database called enclosureName.
    @Column(name = "enclosureName")
    private String enclosureName;

    // Creating a many to one relationship between the Enclosure and Zoo class.
    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;

    // A constructor that takes an animal as a parameter and sets the animal to the
    // animal that is passed
    // in. It also calls the addEnclosureName method and passes in the animal.
    public Enclosure(Animal animal) {
        this.animal = animal;
        addEnclosureName(animal);
    }

    // A constructor that takes an animal and a zoo as parameters and sets the
    // animal and zoo to the animal
    // and zoo that is passed in. It also calls the addEnclosureName method and
    // passes in the animal.
    public Enclosure(Animal animal, Zoo zoo) {
        this.animal = animal;
        addEnclosureName(animal);
        this.zoo = zoo;
    }

    // A constructor that takes a zoo as a parameter and sets the zoo to the zoo
    // that is passed in. It also
    // sets the enclosureName to "Empty enclosure".
    public Enclosure(Zoo zoo) {
        this.zoo = zoo;
        this.enclosureName = "Empty enclosure";
    }

    /**
     * This function takes an animal object and sets the enclosure name to the
     * animal's title plus the word
     * "Enclosure"
     * 
     * @param animal The animal that is being added to the enclosure.
     */

    public void addEnclosureName(Animal animal) {
        this.enclosureName = animal.getTitle() + " Enclosure";
    }

    /**
     * It adds the animal to the enclosure
     * 
     * @param animal The animal to add to the list.
     */
    public void addAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * It removes the animal from the cage.
     * 
     * @param animal The animal to be removed from the cage.
     */
    public void removeAnimal(Animal animal) {
        this.animal = null;
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
     * This function removes the zoo from the zoo keeper
     * 
     * @param zoo Zoo
     */
    public void removeZoo(Zoo zoo) {
        this.zoo = null;
    }

}
