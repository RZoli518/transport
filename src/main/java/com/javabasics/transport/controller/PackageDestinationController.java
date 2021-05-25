package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.Destination;
import com.javabasics.transport.model.Package;
import com.javabasics.transport.model.PackageDestination;
import com.javabasics.transport.repository.PackageDestinationRepository;
import com.javabasics.transport.repository.PackageRepository;
import com.javabasics.transport.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PackageDestinationController {
    @Autowired
    private PackageDestinationRepository packageDestinationRepository;
    private PackageRepository packageRepository;
    private DestinationRepository destinationRepository;

    @GetMapping("packageDestinations/get")
    public List<PackageDestination> getAllPackageDestinations() {
        return this.packageDestinationRepository.findAll();
    }

    @GetMapping("/packageDestinations/getbyid/{id}")
    public ResponseEntity<PackageDestination> getPackageDestinationById(@PathVariable(value = "id") Long packageDestinationId) throws ResourceNotFoundException {
        PackageDestination packageDestination = packageDestinationRepository.findById(packageDestinationId).orElseThrow(() -> new ResourceNotFoundException("PackageDestination not found for this id :: " + packageDestinationId));
        return ResponseEntity.ok().body(packageDestination);
    }

    @PostMapping("/packageDestinations/create")
    public PackageDestination createPackageDestination(@RequestBody Long pack, Long destination) {
        PackageDestination packageDestination = new PackageDestination();
        Package selectedPack = this.packageRepository.getById(pack);
        Destination selectedDestination = this.destinationRepository.getById(destination);
        packageDestination.setDestination(selectedDestination);
        packageDestination.setPack(selectedPack);
        return this.packageDestinationRepository.save(packageDestination);
    }

    @DeleteMapping("/packageDestinations/delete/{id}")
    public Map<String, Boolean> deletePackageDestination(@PathVariable(value = "id") Long packageDestinationId) throws ResourceNotFoundException {
        PackageDestination packageDestination = packageDestinationRepository.findById(packageDestinationId).orElseThrow(() -> new ResourceNotFoundException("PackageDestination not found for this id :: " + packageDestinationId));

        this.packageDestinationRepository.delete(packageDestination);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
