package com.mohk.fleetmanagement.settings.repositories;

import com.mohk.fleetmanagement.settings.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    //for search keywords in countryList from the user typed keyword, c represents column, like%?1 refs to the 1 value typed by the user
    //in the description and capital fields
    @Query(value = "select c from Country c where " +
            "c.description LIKE %?1% or c.capital like %?1%" )
    List<Country> findByKeyword1(String keyword);

    @Query(value = "select c from Country c where " +
            "concat(c.description, c.capital, c.code, c.continent, c.nationality) like %?1%" )
    List<Country> findByKeyword(String keyword);
}
