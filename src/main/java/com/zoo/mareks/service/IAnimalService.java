package com.zoo.mareks.service;

import com.zoo.mareks.models.Animal;

public interface IAnimalService {
    
    public abstract void addAnimalToZooById(Animal animal, int zooId) throws Exception;

    public abstract void removeAnimalByIdFromZooById(int animalId, int zooId) throws Exception;

    public abstract void moveAnimalToDiffZooById(int animalId, int fromZooId, int toZooId) throws Exception;

    public abstract Animal getAnimalById(int id) throws Exception;

}
