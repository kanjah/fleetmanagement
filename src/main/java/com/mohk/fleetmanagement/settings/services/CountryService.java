package com.mohk.fleetmanagement.settings.services;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //for pagination: Page numbering
    public Page<Country> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return countryRepository.findAll(pageable);
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

    //for sorting & pagination
    public Page<Country> findAllWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

        return countryRepository.findAll(pageable);
    }

}



