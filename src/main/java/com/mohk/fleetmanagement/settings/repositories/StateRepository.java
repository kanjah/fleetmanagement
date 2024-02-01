package com.mohk.fleetmanagement.settings.repositories;


import com.mohk.fleetmanagement.settings.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
}
