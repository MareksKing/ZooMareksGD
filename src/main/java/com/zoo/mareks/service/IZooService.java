package com.zoo.mareks.service;

import java.util.Collection;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.FoodAnimal;
import com.zoo.mareks.models.Ticket;
import com.zoo.mareks.models.Zoo;

// The interface for the ZooService class.
public interface IZooService {

    /**
     * This function creates a new zoo.
     * 
     * @param zoo The Zoo object to be created.
     */
    public abstract void createNewZoo(Zoo zoo) throws Exception;

    /**
     * This function deletes a zoo from the database.
     * 
     * @param zooId The id of the zoo to delete.
     */
    public abstract void deleteZooById(int zooId) throws Exception;

    /**
     * Update the zoo with the given id with the given zoo.
     * 
     * @param zooId The id of the zoo to update.
     * @param zoo   The Zoo object that will be updated in the database.
     */
    public abstract void updateZooById(int zooId, Zoo zoo) throws Exception;

    /**
     * This function returns a Zoo object, but it might throw an exception.
     * 
     * @param zooId The id of the zoo you want to get.
     * @return Zoo
     */
    public abstract Zoo getZooById(int zooId) throws Exception;

    /**
     * This function returns a collection of all zoos.
     * 
     * @return A collection of Zoo objects.
     */
    public abstract Collection<Zoo> getAllZoos();

    /**
     * This function returns a collection of all animals in a zoo.
     * 
     * @param zooId The id of the zoo to get the animals from.
     * @return A collection of animals
     */
    public abstract Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception;

    /**
     * This function will buy a new ticket for the user with the given id.
     * 
     * @param ticket The ticket object that you want to buy.
     * @param id     the id of the zoo
     */
    public abstract void buyNewTicket(Ticket ticket, int zooId) throws Exception;

    /**
     * Calculate the average number of visitors per hour in a zoo.
     * 
     * @param zooId The id of the zoo to calculate the average visitor count per
     *              hour for.
     * @return Double
     */
    public abstract Double calcAverageVisitorCountPerHourInZoo(int zooId);

    /**
     * "Calculate the food supplies for all animals in a zoo."
     * 
     * The function is abstract because it doesn't specify how the food supplies are
     * calculated
     * 
     * @param zooId The id of the zoo to calculate the food supplies for.
     * @return A collection of doubles.
     */
    public abstract Collection<Double> calcFoodSuppliesForAnimalsInZoo(int zooId);

    // public abstract double calcFoodSuppliesForFoodstandsInZoo(int zooId);

    /**
     * Update the food count for a specific animal in a specific zoo.
     * 
     * @param zooId      The id of the zoo to update
     * @param foodAnimal The food animal object that contains the food animal id and
     *                   the food animal
     *                   count.
     */
    public abstract void updateZooFoodCount(int zooId, FoodAnimal foodAnimal);
}
