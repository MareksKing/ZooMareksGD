package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Animal;

// Creating a repository for the Animal class.
public interface IAnimalRepo extends CrudRepository<Animal, Integer> {

}
