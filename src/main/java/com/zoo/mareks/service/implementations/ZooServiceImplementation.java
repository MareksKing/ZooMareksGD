package com.zoo.mareks.service.implementations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IZooRepo;
import com.zoo.mareks.service.IZooService;

@Service
public class ZooServiceImplementation  implements IZooService {

    @Autowired
    private IZooRepo zooRepo;

    @Override
    public void createNewZoo(Zoo zoo) throws Exception {
        if(validateZooId(zoo.getZooId())){throw new Exception("Zoo with id " + zoo.getZooId() + " already exist");}
        zooRepo.save(zoo);
        
    }


    @Override
    public void deleteZooById(int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        zooRepo.deleteById(zooId);    
        
    }

    @Override
    public Zoo getZooById(int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        return zooRepo.findById(zooId).get();
    }

    @Override
    public Collection<Zoo> getAllZoos() {
        return (Collection<Zoo>) zooRepo.findAll();
    }

    @Override
    public Collection<Animal> getAllAnimalsByZooById(int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");}
        return zooRepo.findById(zooId).get().getAnimals();
    }

    @Override
    public double calcAverageVisitorCountPerHourInZoo(int zooId) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double calcFoodSuppliesForAnimalsInZoo(int zooId) {
        // TODO Auto-generated method stub
        return 0;
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

}
