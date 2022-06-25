package com.zoo.mareks.service.implementations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.AnimalFoodType;
import com.zoo.mareks.models.Enclosure;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IAnimalRepo;
import com.zoo.mareks.repo.IEnclosureRepo;
import com.zoo.mareks.repo.IZooRepo;
import com.zoo.mareks.service.IAnimalService;

@Service
public class AnimalServiceImplementation implements IAnimalService {

    @Autowired
    private IAnimalRepo animalRepo;

    @Autowired
    private IZooRepo zooRepo;

    @Autowired
    private IEnclosureRepo enclosureRepo;

    @Override
    public void addAnimalToZooById(Animal animal, int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");};
        Zoo zoo = zooRepo.findById(zooId).get();
        if(containsAnimal(animal, zooId)){
            zoo.addAnimal(animal);
            Enclosure enclosure = animalInEnclosure(animal, zoo.getEnclosures());
            enclosure.addAnimal(animal);
            animal.addEnclosure(enclosure);
            enclosureRepo.save(enclosure);
            zooRepo.save(zoo);

        } else {
            zoo.addAnimal(animal);
            animal.addZoo(zoo);
            Enclosure enclosure = new Enclosure(animal, zoo);
            animal.addEnclosure(enclosure);
            zoo.addEnclosure(enclosure);
            enclosureRepo.save(enclosure);
            zooRepo.save(zoo);
        }

        
    }

    @Override
    public void removeAnimalByIdFromZooById(int animalId, int zooId) throws Exception {
        if(!validateZooId(zooId)){throw new Exception("Zoo with id " + zooId + " does not exist");};
        if(!validateAnimalId(animalId)){throw new Exception("Animal with id " + animalId + " does not exist");};

        Zoo zoo = zooRepo.findById(zooId).get();
        Animal animal = animalRepo.findById(animalId).get();

        zoo.removeAnimal(animal);
        animal.removeZoo(zoo);
        zooRepo.save(zoo);
        animalRepo.delete(animal);
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

    private boolean containsAnimal(Animal animal, int zooId){
        Zoo zoo = zooRepo.findById(zooId).get();
        for (Animal inZooAnimal : zoo.getAnimals()) {
            if(inZooAnimal.getTitle() == animal.getTitle() && inZooAnimal.getFood() == animal.getFood()){
                return true;
            }
        }
        return false;
    }

    private Enclosure animalInEnclosure(Animal animal, Collection<Enclosure> enclosures){
        for (Enclosure enclosure : enclosures) {
            Animal animalInEnclosure = enclosure.getAnimal();
            if(animalInEnclosure.getTitle() == animal.getTitle() && animalInEnclosure.getFood() == animal.getFood()){
                return enclosure;
            }
        }
        return new Enclosure(animal);
    }   

}