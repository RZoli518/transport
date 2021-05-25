package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.Vehicle;
import com.javabasics.transport.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("vehicles/get")
    public List<Vehicle> getAllVehicles() {
        return this.vehicleRepository.findAll();
    }

    @GetMapping("/vehicles/getbyid/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));
        return ResponseEntity.ok().body(vehicle);
    }

    @PostMapping("/vehicles/create")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return this.vehicleRepository.save(vehicle);
    }

    @PutMapping("/vehicles/update/{id}")
    public ResponseEntity<Vehicle> updateVehicleName(@PathVariable(value = "id") Long vehicleId, @Validated @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));

        vehicle.setPlate(vehicleDetails.getPlate());
        vehicle.setCapacity(vehicleDetails.getCapacity());

        return ResponseEntity.ok(this.vehicleRepository.save(vehicle));
    }

    @DeleteMapping("/vehicles/delete/{id}")
    public Map<String, Boolean> deleteVehicle(@PathVariable(value = "id") Long vehicleId) throws ResourceNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found for this id :: " + vehicleId));

        this.vehicleRepository.delete(vehicle);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
