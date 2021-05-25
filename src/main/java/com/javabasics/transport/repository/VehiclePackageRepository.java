package com.javabasics.transport.repository;

import com.javabasics.transport.model.VehiclePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePackageRepository extends JpaRepository<VehiclePackage, Long> {
}