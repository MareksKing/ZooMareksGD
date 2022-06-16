package com.zoo.mareks.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
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

public class Ticket {

    @Column(name = "ticketId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int ticketId;

    @OneToOne
    private Visitor visitor;

    @Column(name = "ticketType")
    private TicketType ticketType;

    public Ticket(Visitor visitor, TicketType ticketType) {
        this.ticketType = ticketType;
        this.visitor = visitor;
    }

    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void addVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void removeVisitor() {
        this.visitor = null;
    }


}
