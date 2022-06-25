package com.zoo.mareks.service;

import java.util.Collection;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.FoodAnimal;
import com.zoo.mareks.models.Ticket;
import com.zoo.mareks.models.Zoo;

public interface IZooService {
    
    public abstract void createNewZoo(Zoo zoo) throws Exception;

    public abstract void deleteZooById(int zooId) throws Exception;

    public abstract void updateZooById(int zooId, Zoo zoo) throws Exception;

    public abstract Zoo getZooById(int zooId) throws Exception;

    public abstract Collection<Zoo> getAllZoos();

    public abstract Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception;

    public abstract void buyNewTicket(Ticket ticket) throws Exception;



    public abstract String calcAverageVisitorCountPerHourInZoo(int zooId);

    public abstract Collection<Double> calcFoodSuppliesForAnimalsInZoo(int zooId);

    public abstract double calcFoodSuppliesForFoodstandsInZoo(int zooId);

    public abstract void updateZooFoodCount(int zooId, FoodAnimal foodAnimal);
}
