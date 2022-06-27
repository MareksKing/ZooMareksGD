package com.zoo.mareks.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    // @OneToOne(mappedBy = "visitor", cascade = CascadeType.ALL)
    // private Ticket ticket;

    public Visitor(String visitorName, String visitorSurname, Ticket ticket) {
        this.visitorName = visitorName;
        this.visitorSurname = visitorSurname;
        // this.ticket = ticket;
    }

    public Visitor(String visitorName, String visitorSurname) {
        this.visitorName = visitorName;
        this.visitorSurname = visitorSurname;
    }

    // public void addTicket(Ticket ticket) {
    //     this.ticket = ticket;
    // }

    // public void removeTicket() {
    //     this.ticket = null;
    // }

    
}
