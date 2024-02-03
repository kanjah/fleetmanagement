package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
