package com.zoo.mareks.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IAnimalRepo;
import com.zoo.mareks.repo.IZooRepo;
import com.zoo.mareks.service.IAnimalService;

@Service
public class AnimalServiceImplementation implements IAnimalService {

    @Autowired
    private IAnimalRepo animalRepo;

    @Autowired
    private IZooRepo zooRepo;

    @Override
    public void addAnimalToZooById(Animal animal, int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");};

        Zoo zoo = zooRepo.findById(zooId).get();
        //! parmainit ka var but vairaki tadi pasi dzivnieki viena zoo darza
        if(animal.getZoo() == zoo){throw new Exception("Animal already in zoo");}

        zoo.addAnimal(animal);
        animal.addZoo(zoo);
        
    }

    @Override
    public void removeAnimalByIdFromZooById(int animalId, int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");};
        if(!validateAnimalId(animalId)){throw new Exception("Animal with id " + animalId + " does not exist");};

        Zoo zoo = zooRepo.findById(zooId).get();
        Animal animal = animalRepo.findById(animalId).get();

        zoo.removeAnimal(animal);
        animal.removeZoo(zoo);
    }

    @Override
    public void moveAnimalToDiffZooById(int animalId, int fromZooId, int toZooId) throws Exception {
        if(!validateZooId(fromZooId)){throw new Exception("Zoo with id " + fromZooId + " does not exist");};
        if(!validateZooId(toZooId)){throw new Exception("Zoo with id " + toZooId + " does not exist");};
        if(!validateAnimalId(animalId)){throw new Exception("Animal with id " + animalId + " does not exist");};

        Zoo fromZoo = zooRepo.findById(fromZooId).get();
        Zoo toZoo = zooRepo.findById(toZooId).get();
        Animal animal = animalRepo.findById(animalId).get();

        fromZoo.removeAnimal(animal);
        animal.removeZoo(fromZoo);

        toZoo.addAnimal(animal);
        animal.addZoo(toZoo);
        
    }

    @Override
    public Animal getAnimalById(int id) throws Exception {
        if(!validateAnimalId(id)){throw new Exception("Animal with id " + id + " does not exist");};
        return animalRepo.findById(id).get();
    }

    private boolean validateZooId(int zooId) throws Exception {
        if (zooId < 0) {
            throw new Exception("Zoo id cannot be negative");
        }
        return zooRepo.existsById(zooId);
    }

    private boolean validateAnimalId(int animalId) throws Exception {
        if (animalId < 0) {
            throw new Exception("Animal id cannot be negative");
        }
        return animalRepo.existsById(animalId);
    }

}