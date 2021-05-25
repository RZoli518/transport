package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.VehicleDriver;
import com.javabasics.transport.repository.VehicleDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VehicleDriverController {
    @Autowired
    private VehicleDriverRepository vehicleDriverRepository;

    @GetMapping("vehicleDrivers/get")
    public List<VehicleDriver> getAllVehicleDrivers() {
        return this.vehicleDriverRepository.findAll();
    }

    @GetMapping("/vehicleDrivers/getbyid/{id}")
    public ResponseEntity<VehicleDriver> getVehicleDriverById(@PathVariable(value = "id") Long vehicleDriverId) throws ResourceNotFoundException {
        VehicleDriver vehicleDriver = vehicleDriverRepository.findById(vehicleDriverId).orElseThrow(() -> new ResourceNotFoundException("VehicleDriver not found for this id :: " + vehicleDriverId));
        return ResponseEntity.ok().body(vehicleDriver);
    }

    @PostMapping("/vehicleDrivers/create")
    public VehicleDriver createVehicleDriver(@RequestBody VehicleDriver vehicleDriver) {
        return this.vehicleDriverRepository.save(vehicleDriver);
    }

    @PutMapping("/vehicleDrivers/updatename/{id}")
    public ResponseEntity<VehicleDriver> updateVehicleDriverName(@PathVariable(value = "id") Long vehicleDriverId, @RequestParam("packagecount") Integer packageCount) throws ResourceNotFoundException {
        VehicleDriver vehicleDriver = vehicleDriverRepository.findById(vehicleDriverId).orElseThrow(() -> new ResourceNotFoundException("VehicleDriver not found for this id :: " + vehicleDriverId));

        vehicleDriver.setPackageCount(packageCount);

        return ResponseEntity.ok(this.vehicleDriverRepository.save(vehicleDriver));
    }

    @DeleteMapping("/vehicleDrivers/delete/{id}")
    public Map<String, Boolean> deleteVehicleDriver(@PathVariable(value = "id") Long vehicleDriverId) throws ResourceNotFoundException {
        VehicleDriver vehicleDriver = vehicleDriverRepository.findById(vehicleDriverId).orElseThrow(() -> new ResourceNotFoundException("VehicleDriver not found for this id :: " + vehicleDriverId));

        this.vehicleDriverRepository.delete(vehicleDriver);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}