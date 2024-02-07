package com.mohk.fleetmanagement.fleet.restapis;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
public class CountryRestController {
    @Autowired
    private CountryService countryService;

    // return list of all countries from the service
    @GetMapping("/api/settings/countries")
    //passing the model country to the other pages so that country/countryList can be displayed to the user(UI)
    public  List<Country> getAll(Model model){

        //list of countries from getAll() from CountryService
        List<Country> countries = countryService.findAll();

       //display the countryList html page i.e(template/setting/countryList)
        return countries;
    }
}
