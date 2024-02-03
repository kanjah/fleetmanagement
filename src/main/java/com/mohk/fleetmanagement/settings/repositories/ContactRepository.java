package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
