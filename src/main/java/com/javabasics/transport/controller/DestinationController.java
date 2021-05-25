package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.Destination;
import com.javabasics.transport.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DestinationController {
    @Autowired
    private DestinationRepository destinationRepository;

    @GetMapping("destinations/get")
    public List<Destination> getAllDestinations() {
        return this.destinationRepository.findAll();
    }

    @GetMapping("/destinations/getbyid/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable(value = "id") Long destinationId) throws ResourceNotFoundException {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new ResourceNotFoundException("Destination not found for this id :: " + destinationId));
        return ResponseEntity.ok().body(destination);
    }

    @PostMapping("/destinations/create")
    public Destination createDestination(@RequestBody Destination destination) {
        return this.destinationRepository.save(destination);
    }

    @DeleteMapping("/destinations/delete/{id}")
    public Map<String, Boolean> deleteDestination(@PathVariable(value = "id") Long destinationId) throws ResourceNotFoundException {
        Destination destination = destinationRepository.findById(destinationId).orElseThrow(() -> new ResourceNotFoundException("Destination not found for this id :: " + destinationId));

        this.destinationRepository.delete(destination);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
