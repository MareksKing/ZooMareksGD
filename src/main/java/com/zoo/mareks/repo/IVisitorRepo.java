package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Visitor;

// Creating a repository for the Visitor class.
public interface IVisitorRepo extends CrudRepository<Visitor, Integer> {

}
