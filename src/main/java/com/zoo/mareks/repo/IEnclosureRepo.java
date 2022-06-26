package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Enclosure;


// Creating a repository for the Enclosure class.
public interface IEnclosureRepo extends CrudRepository<Enclosure, Integer> {

}
