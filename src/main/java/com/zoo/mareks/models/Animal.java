package com.zoo.mareks.models;

import javax.persistence.Entity;
import javax.persistence.Table;

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
    private int id;
    private String title;
    private String food;

    public Animal(String title, String food) {
        this.title = title;
        this.food = food;
    }
    
}
