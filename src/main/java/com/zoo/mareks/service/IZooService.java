package com.zoo.mareks.service;

import java.util.Collection;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Zoo;

public interface IZooService {
    
    public abstract void createNewZoo(Zoo zoo) throws Exception;

    public abstract void deleteZooById(int zooId) throws Exception;

    public abstract Zoo getZooById(int zooId) throws Exception;

    public abstract Collection<Zoo> getAllZoos();

    public abstract Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception;

    public abstract double calcAverageVisitorCountPerHourInZoo(int zooId);

    public abstract double calcFoodSuppliesForAnimalsInZoo(int zooId);

    public abstract double calcFoodSuppliesForFoodstandsInZoo(int zooId);
}
