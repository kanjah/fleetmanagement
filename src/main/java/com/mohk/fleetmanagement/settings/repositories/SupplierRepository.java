package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository  extends JpaRepository<Supplier, Integer> {
}
