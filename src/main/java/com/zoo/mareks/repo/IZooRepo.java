package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;

public interface IZooRepo extends CrudRepository<Zoo, Integer> {

    Zoo findByTown(Town town);

}
