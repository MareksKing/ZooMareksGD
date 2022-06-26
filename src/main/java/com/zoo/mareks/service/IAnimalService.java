package com.zoo.mareks.service;

import com.zoo.mareks.models.Animal;

public interface IAnimalService {
    
    /**
     * This function adds an animal to a zoo by its id.
     * 
     * @param animal The animal object to be added to the zoo.
     * @param zooId The id of the zoo to add the animal to.
     */
    public abstract void addAnimalToZooById(Animal animal, int zooId) throws Exception;

    /**
     * "Remove the animal with the given id from the zoo with the given id."
     * 
     * @param animalId The id of the animal to be removed from the zoo.
     * @param zooId The id of the zoo to remove the animal from.
     */
    public abstract void removeAnimalByIdFromZooById(int animalId, int zooId) throws Exception;

    /**
     * "Move an animal from one zoo to another."
     * 
     * @param animalId The id of the animal to move
     * @param fromZooId The id of the zoo that the animal is currently in.
     * @param toZooId The id of the zoo to move the animal to.
     */
    public abstract void moveAnimalToDiffZooById(int animalId, int fromZooId, int toZooId) throws Exception;

    /**
     * This function returns an Animal object, but it might throw an exception.
     * 
     * @param id The id of the animal to retrieve.
     * @return An Animal object
     */
    public abstract Animal getAnimalById(int id) throws Exception;

}
