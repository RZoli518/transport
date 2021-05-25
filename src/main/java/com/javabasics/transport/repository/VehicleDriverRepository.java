package com.javabasics.transport.repository;

import com.javabasics.transport.model.VehicleDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDriverRepository extends JpaRepository<VehicleDriver, Long> {
}
