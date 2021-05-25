package com.javabasics.transport.repository;

import com.javabasics.transport.model.PackageDestination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDestinationRepository extends JpaRepository<PackageDestination, Long> {
}
