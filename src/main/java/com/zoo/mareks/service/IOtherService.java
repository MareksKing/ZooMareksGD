package com.zoo.mareks.service;

import java.util.Collection;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;

public interface IOtherService {
    
    /**
     * "Select all zoos that have the given animal."
     * 
     * 
     * @param animal The animal to search for.
     * @return A collection of Zoo objects.
     */
    public abstract Collection<Zoo> selectAllZoosByAnimal(Animal animal);
    
    /**
     * Search for a zoo by address.
     * 
     * @param town The town to search for a zoo in.
     * @return A Zoo object.
     */
    public abstract Zoo searchForZooByAddress(Town town);

    /**
     * Return a collection of all animals in all zoos.
     * 
     * @return A collection of all animals in all zoos.
     */
    public abstract Collection<Animal> allAnimalsInAllZoos();
}
