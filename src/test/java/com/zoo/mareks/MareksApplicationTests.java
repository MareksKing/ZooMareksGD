package com.zoo.mareks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.zoo.mareks.models.Animal;
import com.zoo.mareks.models.AnimalFood;

@SpringBootTest
class MareksApplicationTests {

	@Test
	public void initAnimalEntity(){
		
		Animal animal = new Animal("Lauva", AnimalFood.Gala);

		System.out.println(animal);
	}

}
