package com.zoo.mareks.service.implementations;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoo.mareks.models.Animal;
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

    /**
     * If the animal is not in the zoo, add it to the zoo and create a new enclosure
     * for it. If the animal
     * is in the zoo, add it to the zoo and add it to the enclosure it is already in
     * 
     * @param animal Animal object
     * @param zooId  id of the zoo the animal is supposed to be added to
     */
    @Override
    public void addAnimalToZooById(Animal animal, int zooId) throws Exception {
        if (!zooRepo.existsById(zooId)) {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }
        ;
        Zoo zoo = zooRepo.findById(zooId).get();
        if (containsAnimal(animal, zooId)) {
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

    /**
     * If the zoo and animal exist, remove the animal from the zoo and delete the
     * animal.
     * 
     * @param animalId 1
     * @param zooId    1
     */
    @Override
    public void removeAnimalByIdFromZooById(int animalId, int zooId) throws Exception {
        if (!zooRepo.existsById(zooId)) {
            throw new Exception("Zoo with id " + zooId + " does not exist");
        }
        ;
        if (!animalRepo.existsById(animalId)) {
            throw new Exception("Animal with id " + animalId + " does not exist");
        }
        ;

        Zoo zoo = zooRepo.findById(zooId).get();
        Animal animal = animalRepo.findById(animalId).get();

        zoo.removeAnimal(animal);
        animal.removeZoo(zoo);
        zooRepo.save(zoo);
        animalRepo.delete(animal);
    }

    /**
     * It removes an animal from one zoo and adds it to another
     * 
     * @param animalId  the id of the animal to be moved
     * @param fromZooId the id of the zoo the animal is currently in
     * @param toZooId   1
     */
    @Override
    public void moveAnimalToDiffZooById(int animalId, int fromZooId, int toZooId) throws Exception {
        if (!zooRepo.existsById(fromZooId)) {
            throw new Exception("Zoo with id " + fromZooId + " does not exist");
        }
        ;
        if (!zooRepo.existsById(toZooId)) {
            throw new Exception("Zoo with id " + toZooId + " does not exist");
        }
        ;
        if (!animalRepo.existsById(animalId)) {
            throw new Exception("Animal with id " + animalId + " does not exist");
        }
        ;

        Zoo fromZoo = zooRepo.findById(fromZooId).get();
        Zoo toZoo = zooRepo.findById(toZooId).get();
        Animal animal = animalRepo.findById(animalId).get();
        Enclosure enclosure = animalInEnclosure(animal, fromZoo.getEnclosures());

        fromZoo.removeAnimal(animal);
        animal.removeZoo(fromZoo);
        enclosure.removeZoo(fromZoo);

        zooRepo.save(fromZoo);

        toZoo.addAnimal(animal);
        animal.addZoo(toZoo);
        enclosure.addZoo(toZoo);

        zooRepo.save(toZoo);

    }

    /**
     * If the animal with the given id does not exist, throw an exception.
     * Otherwise, return the animal
     * with the given id
     * 
     * @param id the id of the animal to be retrieved
     * @return The animal with the id that was passed in.
     */
    @Override
    public Animal getAnimalById(int id) throws Exception {
        if (!animalRepo.existsById(id)) {
            throw new Exception("Animal with id " + id + " does not exist");
        }
        ;
        return animalRepo.findById(id).get();
    }

    /**
     * It checks if the animal is already in the zoo
     * 
     * @param animal Animal
     * @param zooId  the id of the zoo
     * @return A boolean value.
     */
    private boolean containsAnimal(Animal animal, int zooId) {
        Zoo zoo = zooRepo.findById(zooId).get();
        for (Animal inZooAnimal : zoo.getAnimals()) {
            if (inZooAnimal.getTitle() == animal.getTitle() && inZooAnimal.getFood() == animal.getFood()) {
                return true;
            }
        }
        return false;
    }

    /**
     * "If the animal is in an enclosure, return the enclosure, otherwise return a
     * new enclosure with the
     * animal."
     * 
     * @param animal     Animal
     * @param enclosures a collection of enclosures
     * @return The enclosure that the animal is in.
     */
    private Enclosure animalInEnclosure(Animal animal, Collection<Enclosure> enclosures) {
        for (Enclosure enclosure : enclosures) {
            Animal animalInEnclosure = enclosure.getAnimal();
            if (animalInEnclosure.getTitle() == animal.getTitle() && animalInEnclosure.getFood() == animal.getFood()) {
                return enclosure;
            }
        }
        return new Enclosure(animal);
    }

}