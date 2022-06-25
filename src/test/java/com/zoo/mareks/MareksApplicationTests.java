package com.zoo.mareks;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.AnimalFoodType;
import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IAnimalRepo;
import com.zoo.mareks.repo.ITicketRepo;
import com.zoo.mareks.repo.IVisitorRepo;
import com.zoo.mareks.repo.IZooRepo;

@SpringBootTest
class MareksApplicationTests {

	@Test
	public void initAnimalEntity(IAnimalRepo animalRepo, IZooRepo zooRepo, IVisitorRepo visitorRepo, ITicketRepo ticketRepo) {
		Zoo zoo = new Zoo("TestZoo", Town.Pūņas, "TestZooPhone");
		zooRepo.save(zoo);
		Animal animal = new Animal("Lauva", AnimalFoodType.Gala, 5);
		zoo.setAnimals(Arrays.asList(animal));
		animalRepo.save(animal);
		System.out.println(animal);
	}

}
