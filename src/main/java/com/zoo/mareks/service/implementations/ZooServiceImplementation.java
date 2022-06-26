package com.zoo.mareks.service.implementations;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.FoodAnimal;
import com.zoo.mareks.models.Ticket;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IFoodAnimalRepo;
import com.zoo.mareks.repo.ITicketRepo;
import com.zoo.mareks.repo.IZooRepo;
import com.zoo.mareks.service.IZooService;

@Service
public class ZooServiceImplementation implements IZooService {

    @Autowired
    private IZooRepo zooRepo;

    @Autowired
    private ITicketRepo ticketRepo;
    /**
     * This function returns a collection of all zoos that contain a specific
     * animal.
     * 
     * @param animal Animal
     * @return A collection of all zoos that contain the animal.
     */

    @Autowired
    private IFoodAnimalRepo foodRepo;

    /**
     * If the zoo with the given id already exists, throw an exception. Otherwise,
     * save the zoo
     * 
     * @param zoo Zoo object
     */
    @Override
    public void createNewZoo(Zoo zoo) throws Exception {
        if (zooRepo.existsById(zoo.getZooId())) {
            throw new Exception("Zoo with id " + zoo.getZooId() + " already exist");
        }
        zooRepo.save(zoo);
    }

    /**
     * "This function takes a ticket and a zooId, finds the zoo with that zooId,
     * adds the ticket to the
     * zoo, adds the zoo to the ticket, and saves the zoo."
     * 
     * @param ticket Ticket
     * @param zooId  the id of the zoo that the ticket is being bought for
     */
    @Override
    public void buyNewTicket(Ticket ticket, int zooId) throws Exception {
        Zoo zoo = zooRepo.findById(zooId).get();
        zoo.addTicket(ticket);
        ticket.addZoo(zoo);
        zooRepo.save(zoo);
    }

    /**
     * If the zoo with the given id does not exist, throw an exception. Otherwise,
     * delete the zoo with the
     * given id
     * 
     * @param zooId The id of the zoo to be deleted
     */
    @Override
    public void deleteZooById(int zooId) throws Exception {
        if (!zooRepo.existsById(zooId)) {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }
        zooRepo.deleteById(zooId);
    }

    /**
     * If the zoo exists, update the zoo with the new information
     * 
     * @param zooId the id of the zoo to be updated
     * @param temp  Zoo
     */
    @Override
    public void updateZooById(int zooId, Zoo temp) throws Exception {
        if (zooRepo.existsById(zooId)) {
            Zoo zoo = zooRepo.findById(zooId).get();

            if (!zoo.getName().equals(temp.getName())) {
                zoo.setName(temp.getName());
            }

            if (temp.getTown() != zoo.getTown()) {
                zoo.setTown(temp.getTown());
            }

            if (temp.getPhone() != zoo.getPhone()) {
                zoo.setPhone(temp.getPhone());
            }
            zooRepo.save(zoo);
        } else {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }

    }

    /**
     * If the zoo with the given id does not exist, throw an exception. Otherwise,
     * return the zoo with the
     * given id
     * 
     * @param zooId The id of the zoo to be retrieved
     * @return Zoo
     */
    @Override
    public Zoo getZooById(int zooId) throws Exception {
        if (!zooRepo.existsById(zooId)) {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }
        return zooRepo.findById(zooId).get();
    }

    /**
     * This function returns a collection of all the zoos in the database.
     * 
     * @return A collection of Zoo objects.
     */
    @Override
    public Collection<Zoo> getAllZoos() {
        return (Collection<Zoo>) zooRepo.findAll();
    }

    /**
     * "If the zoo with the given id does not exist, throw an exception. Otherwise,
     * return the animals in
     * the zoo with the given id."
     * 
     * 
     * @param zooId the id of the zoo
     * @return A collection of animals.
     */
    @Override
    public Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception {
        if (!zooRepo.existsById(zooId)) {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }
        return zooRepo.findById(zooId).get().getAnimals();
    }

    /**
     * Calculate the average number of visitors per hour in a zoo.
     * 
     * @param zooId the id of the zoo
     * @return The average number of visitors per hour in the zoo.
     */
    @Override
    public Double calcAverageVisitorCountPerHourInZoo(int zooId) {
        Zoo zoo = zooRepo.findById(zooId).get();
        double avg = zoo.getAllTickets().size() / 8;
        return avg;
    }

    /**
     * It calculates the average days of food supply for each animal in a zoo
     * 
     * @param zooId the id of the zoo
     * @return A collection of doubles.
     */
    @Override
    public Collection<Double> calcFoodSuppliesForAnimalsInZoo(int zooId) {
        Collection<FoodAnimal> food = (Collection<FoodAnimal>) foodRepo.findAll();
        Collection<Double> averageDays = new ArrayList<Double>();
        Collection<Animal> allAnimals = zooRepo.findById(zooId).get().getAnimals();

        for (FoodAnimal foodAnimal : food) {
            for (Animal animal : allAnimals) {
                if (animal.getFood() == foodAnimal.getFoodType()) {
                    double averageDayLast = foodAnimal.getQuantity() / (animal.getAnimalCount() * 3);
                    averageDays.add(averageDayLast);
                }
            }
        }

        return averageDays;
    }

    // @Override
    // public double calcFoodSuppliesForFoodstandsInZoo(int zooId) {
    // // TODO Auto-generated method stub
    // return 0;
    // }

    /**
     * This function updates the food count of a zoo by the zoo's id
     * 
     * @param zooId      the id of the zoo
     * @param foodAnimal FoodAnimal
     */
    @Override
    public void updateZooFoodCount(int zooId, FoodAnimal foodAnimal) {
        Zoo zoo = zooRepo.findById(zooId).get();
        zoo.addFood(foodAnimal);
        foodAnimal.addZoo(zoo);
        foodRepo.save(foodAnimal);

    }

}
