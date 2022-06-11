package com.zoo.mareks.repo;

import org.springframework.data.repository.CrudRepository;

import com.zoo.mareks.models.Ticket;


public interface ITicketRepo extends CrudRepository<Ticket, Integer> {

}
