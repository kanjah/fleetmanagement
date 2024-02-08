package com.mohk.fleetmanagement.settings.services;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

//    Fetch all countries
    public List<Country> findAll(){
        return countryRepository.findAll();
    }

    //save new country
    public void save(Country country){
        countryRepository.save(country);
    }

    //delete country
    public void delete(Integer id){
        countryRepository.deleteById(id);
    }

    //edit cuntry
    public Country getById(Integer id) {
        return countryRepository.findById(id).orElse(null);
    }

    //search by keyword in countryList
    public List<Country> findByKeyword(String keyword){
        return countryRepository.findByKeyword(keyword);
    }


}



