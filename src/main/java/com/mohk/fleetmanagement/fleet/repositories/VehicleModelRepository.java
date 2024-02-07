package com.mohk.fleetmanagement.fleet.repositories;

import com.mohk.fleetmanagement.fleet.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
}
