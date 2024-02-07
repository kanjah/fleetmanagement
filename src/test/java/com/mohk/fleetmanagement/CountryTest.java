package com.mohk.fleetmanagement;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryTest {
    @Autowired
    private CountryRepository repository;

    //FINDByID
    @Test
    public void testFindByID(){
    Country country = repository.findById(1).orElse(null);
    assertNotNull(country);

    }
    @Test
    public void testFindByNull(){
        Country country = repository.findById(33).orElse(null);
        assertNull(country);
    }



}
