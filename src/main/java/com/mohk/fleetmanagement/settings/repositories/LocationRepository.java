package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
}
