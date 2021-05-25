package com.javabasics.transport.model;

import javax.persistence.*;

@Entity
@IdClass(VehiclePackage.class)
public class VehiclePackage implements java.io.Serializable{
    @Id
    @ManyToOne
    private Package pack;

    @Id
    @ManyToOne
    private Vehicle vehicle;

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
