package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.Driver;
import com.javabasics.transport.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping("drivers/get")
    public List<Driver> getAllDrivers() {
        return this.driverRepository.findAll();
    }

    @GetMapping("/drivers/getbyid/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Long driverId) throws ResourceNotFoundException {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
        return ResponseEntity.ok().body(driver);
    }

    @PostMapping("/drivers/create")
    public Driver createDriver(@RequestBody Driver driver) {
        return this.driverRepository.save(driver);
    }

    @PutMapping("/drivers/updatename/{id}")
    public ResponseEntity<Driver> updateDriverName(@PathVariable(value = "id") Long driverId, @Validated @RequestBody Driver driverDetails) throws ResourceNotFoundException {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));

        driver.setFirstName(driverDetails.getFirstName());
        driver.setLastName(driverDetails.getLastName());

        return ResponseEntity.ok(this.driverRepository.save(driver));
    }

    @DeleteMapping("/drivers/delete/{id}")
    public Map<String, Boolean> deleteDriver(@PathVariable(value = "id") Long driverId) throws ResourceNotFoundException {
        Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));

        this.driverRepository.delete(driver);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
