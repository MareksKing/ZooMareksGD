package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.FoodAnimal;

// Creating a repository for the FoodAnimal class.
public interface IFoodAnimalRepo extends CrudRepository<FoodAnimal, Integer> {
    
}
