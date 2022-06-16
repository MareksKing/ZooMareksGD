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

    @Column(name = "zooId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int zooId;
        
    @Column(name = "zooName")
    private String name;

    @Column(name = "zooAddress")
    private String address;

    @Column(name = "zooPhone")
    private String phone;

    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL)
    private Collection<Enclosure> enclosures = new ArrayList<Enclosure>();

    @OneToMany(mappedBy = "zoo", cascade = CascadeType.ALL)
    private Collection<Animal> animals = new ArrayList<Animal>();

    public Zoo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
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


}

