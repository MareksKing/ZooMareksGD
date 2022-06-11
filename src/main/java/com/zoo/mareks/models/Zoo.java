package com.zoo.mareks.models;

import java.util.Collection;

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

    // @Column(name = "zooId")
    
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // @Setter(value = AccessLevel.NONE)
    // private int zooId;
        
    @Column(name = "zooName")
    @Id
    private String name;

    @Column(name = "zooAddress")
    private String address;

    @Column(name = "zooPhone")
    private String phone;

    @OneToMany(mappedBy = "zoo")
    private Collection<Enclosure> enclosure;

    @OneToMany(mappedBy = "zoo")
    private Collection<Animal> animal;

    public Zoo(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


}

