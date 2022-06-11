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

    @Column(name = "enclosureId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int enclosureId;

    @OneToOne(cascade = CascadeType.ALL)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "zooName")
    private Zoo zoo;
    
    public Enclosure(Animal animal) {
        this.animal = animal;
    }
    


}
