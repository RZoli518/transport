package com.javabasics.transport.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "licenceplate", nullable = false)
    private String plate;

    @Column(name = "capacity")
    private Float capacity;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VehicleDriver> vehicleDriver;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VehiclePackage> vehiclePackage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Float getCapacity() {
        return capacity;
    }

    public void setCapacity(Float capacity) {
        this.capacity = capacity;
    }

    public Set<VehicleDriver> getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(Set<VehicleDriver> vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    public Set<VehiclePackage> getVehiclePackage() {
        return vehiclePackage;
    }

    public void setVehiclePackage(Set<VehiclePackage> vehiclePackage) {
        this.vehiclePackage = vehiclePackage;
    }
}
