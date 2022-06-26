package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Ticket;


// Creating a repository for the Ticket class.
public interface ITicketRepo extends CrudRepository<Ticket, Integer> {

}
