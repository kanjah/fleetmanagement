package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer>
{
}
