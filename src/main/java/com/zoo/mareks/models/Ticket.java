package com.zoo.mareks.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table
@Entity
@NoArgsConstructor
@ToString

public class Ticket {

    // Creating a column in the database called ticketId, which is the primary key,
    // and it is auto
    // generated.
    @Column(name = "ticketId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int ticketId;

    // Creating a column in the database called visitorName.
    @Column(name = "visitorName")
    private String visitorName;

    // Creating a column in the database called visitorSurname.
    @Column(name = "visitorSurname")
    private String visitorSurname;

    // Creating a column in the database called ticketType.
    @Column(name = "ticketType")
    private TicketType ticketType;

    // Creating a column in the database called zooId, which is a foreign key.
    @ManyToOne
    @JoinColumn(name = "zooId")
    private Zoo zoo;

    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Ticket(String name, String surname, TicketType ticketType) {
        this.visitorName = name;
        this.visitorSurname = surname;
        this.ticketType = ticketType;
    }

    /**
     * This function adds a zoo to the zoo list
     * 
     * @param zoo The Zoo object that the animal belongs to.
     */
    public void addZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     * This function removes a zoo from the list of zoos
     * 
     * @param zoo The zoo that the animal is in.
     */
    public void removeZoo(Zoo zoo) {
        this.zoo = zoo;
    }
}
