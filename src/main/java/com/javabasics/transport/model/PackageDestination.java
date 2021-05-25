package com.javabasics.transport.model;

import javax.persistence.*;

@Entity
@IdClass(PackageDestination.class)
public class PackageDestination implements java.io.Serializable{
    @Id
    @ManyToOne
    private Destination destination;

    @Id
    @ManyToOne
    private Package pack;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }
}
