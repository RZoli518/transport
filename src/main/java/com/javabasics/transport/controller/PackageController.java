package com.javabasics.transport.controller;

import com.javabasics.transport.exception.ResourceNotFoundException;
import com.javabasics.transport.model.Package;
import com.javabasics.transport.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PackageController {
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private void setPackageRepository(PackageRepository packageRepository) {this.packageRepository = packageRepository;}


    @GetMapping("packs/get")
    public List<Package> getAllPackages() {
        return this.packageRepository.findAll();
    }

    @GetMapping("/packs/getbyid/{id}")
    public ResponseEntity<Package> getPackageById(@PathVariable(value = "id") Long packId) throws ResourceNotFoundException {
        Package pack = packageRepository.findById(packId).orElseThrow(() -> new ResourceNotFoundException("Package not found for this id :: " + packId));
        return ResponseEntity.ok().body(pack);
    }

    @PostMapping("/packs/create")
    public Package createPackage(@RequestBody Package pack) {
        return this.packageRepository.save(pack);
    }

    @DeleteMapping("/packs/delete/{id}")
    public Map<String, Boolean> deletePackage(@PathVariable(value = "id") Long packId) throws ResourceNotFoundException {
        Package pack = packageRepository.findById(packId).orElseThrow(() -> new ResourceNotFoundException("Package not found for this id :: " + packId));

        this.packageRepository.delete(pack);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
