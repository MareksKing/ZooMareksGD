package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Animal;

public interface IAnimalRepo extends CrudRepository<Animal, Integer> {

}
