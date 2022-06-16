package com.zoo.mareks;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.AnimalFood;
import com.zoo.mareks.models.Zoo;
import com.zoo.mareks.repo.IAnimalRepo;
import com.zoo.mareks.repo.ITicketRepo;
import com.zoo.mareks.repo.IVisitorRepo;
import com.zoo.mareks.repo.IZooRepo;

@SpringBootTest
class MareksApplicationTests {

	@Test
	public void initAnimalEntity(IAnimalRepo animalRepo, IZooRepo zooRepo, IVisitorRepo visitorRepo, ITicketRepo ticketRepo) {
		Zoo zoo = new Zoo("TestZoo", "TestZooAddress", "TestZooPhone");
		zooRepo.save(zoo);
		Animal animal = new Animal("Lauva", AnimalFood.Gala);
		zoo.setAnimals(Arrays.asList(animal));
		animalRepo.save(animal);
		System.out.println(animal);
	}

}
