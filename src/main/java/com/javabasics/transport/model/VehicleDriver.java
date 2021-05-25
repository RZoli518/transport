package com.javabasics.transport.model;

import javax.persistence.*;

@Entity
@IdClass(VehicleDriver.class)
public class VehicleDriver implements java.io.Serializable{
    @Id
    @ManyToOne
    private Vehicle vehicle;

    @Id
    @ManyToOne
    private  Driver driver;

    @Column(name = "packagecount")
    private Integer packageCount;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Integer getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }
}
