package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Town;
import com.zoo.mareks.models.Zoo;

// Creating a repository for the Zoo class.
public interface IZooRepo extends CrudRepository<Zoo, Integer> {

    /**
     * Find a zoo by town.
     * 
     * @param town The town to search for.
     * @return The zoo in the specific town.
     */
    Zoo findByTown(Town town);

}
