package com.zoo.mareks.service.implementations;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IAnimalRepo;
import com.zoo.mareks.repo.IZooRepo;
import com.zoo.mareks.service.IOtherService;

@Service
public class OtherServiceImplementation implements IOtherService {

    @Autowired
    private IZooRepo zooRepo;
    
    @Autowired
    private IAnimalRepo animalRepo;


    @Override
    public Collection<Zoo> selectAllZoosByAnimal(Animal animal) {
        Collection<Zoo> allZoosWithAnimal = new ArrayList<Zoo>();
        for (Zoo zoo : zooRepo.findAll()) {
            if (zoo.getAnimals().contains(animal)) {
                allZoosWithAnimal.add(zoo);
            }
        }
        return allZoosWithAnimal;
    }

    @Override
    public Zoo searchForZooByAddress(Town town) {
        return zooRepo.findByTown(town);
    }

    @Override
    public Collection<Animal> allAnimalsInAllZoos(){
        Collection<Animal> allAnimals = (Collection<Animal>) animalRepo.findAll();
        return allAnimals;
    }

    
}
