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
public class ZooServiceImplementation  implements IZooService {

    @Autowired
    private IZooRepo zooRepo;

    @Autowired
    private ITicketRepo ticketRepo;

    @Autowired
    private IFoodAnimalRepo foodRepo;

    @Override
    public void createNewZoo(Zoo zoo) throws Exception {
        if(validateZooId(zoo.getZooId())){throw new Exception("Zoo with id " + zoo.getZooId() + " already exist");}
        zooRepo.save(zoo);
        
    }
    @Override
    public void buyNewTicket(Ticket ticket) throws Exception {
        ticketRepo.save(ticket);
        
    }


    @Override
    public void deleteZooById(int zooId) throws Exception {
        if(!zooRepo.existsById(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        zooRepo.deleteById(zooId);      
    }

    @Override
    public void updateZooById(int zooId, Zoo temp) throws Exception {
        if(zooRepo.existsById(zooId)) {
            Zoo zoo = zooRepo.findById(zooId).get();
            
            if(!zoo.getName().equals(temp.getName())) {
                zoo.setName(temp.getName());						
            } 
            
            if(temp.getTown() != zoo.getTown()) {
                zoo.setTown(temp.getTown());						
            }
            
            if(temp.getPhone() != zoo.getPhone()) {
                zoo.setPhone(temp.getPhone());						
            } 
            zooRepo.save(zoo);
        }else {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        } 
      
    }

    @Override
    public Zoo getZooById(int zooId) throws Exception {
        if(!zooRepo.existsById(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        return zooRepo.findById(zooId).get();
    }

    @Override
    public Collection<Zoo> getAllZoos() {
        return (Collection<Zoo>) zooRepo.findAll();
    }

    @Override
    public Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception {
        if(!zooRepo.existsById(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        return zooRepo.findById(zooId).get().getAnimals();
    }

    @Override
    public String calcAverageVisitorCountPerHourInZoo(int zooId) {
        Zoo zoo = zooRepo.findById(zooId).get();
        float average = zoo.getAllTickets().size()/3;
        String avg = Float.toString(average);
        return avg;
    }

    @Override
    public Collection<Double> calcFoodSuppliesForAnimalsInZoo(int zooId) {
        Collection<FoodAnimal> food = (Collection<FoodAnimal>) foodRepo.findAll();
        Collection<Double> averageDays = new ArrayList<Double>();
        Collection<Animal> allAnimals = zooRepo.findById(zooId).get().getAnimals();

        for (FoodAnimal foodAnimal : food) {
            for (Animal animal : allAnimals) {
                if(animal.getFood() == foodAnimal.getFoodType()){
                    double averageDayLast = foodAnimal.getQuantity()/(animal.getAnimalCount()*3);
                    averageDays.add(averageDayLast);
                }
            }
        }

        return averageDays;
    }

    @Override
    public double calcFoodSuppliesForFoodstandsInZoo(int zooId) {
        // TODO Auto-generated method stub
        return 0;
    }

    private boolean validateZooId(int zooId) throws Exception {
        if (zooId < 0) {
            throw new Exception("Zoo id cannot be negative");
        }
        return zooRepo.existsById(zooId);
    }

    @Override
    public void updateZooFoodCount(int zooId, FoodAnimal foodAnimal){
        Zoo zoo = zooRepo.findById(zooId).get();

        zoo.addFood(foodAnimal);
    }



}
