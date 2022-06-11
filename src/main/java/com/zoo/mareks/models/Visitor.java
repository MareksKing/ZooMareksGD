package com.zoo.mareks.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

public class Visitor {

    @Column(name = "visitorId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int visitorId;

    @Column(name = "visitorName")
    private String visitorName;

    @Column(name = "visitorSurname")
    private String visitorSurname;

    @OneToOne(mappedBy = "visitor")
    @JoinColumn(name = "ticketId")
    private Ticket ticket;

    public Visitor(String visitorName, String visitorSurname, TicketType ticketType) {
        this.visitorName = visitorName;
        this.visitorSurname = visitorSurname;
        this.ticket.setTicketType(ticketType);
    }

    
}
