package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    // return list of all countries from the service
    @GetMapping("/countries")
    //passing the model country to the other pages so that country/countryList can be displayed to the user(UI)
      public String getAll(Model model){

        //list of countries from getAll() from CountryService
       List<Country> countries = countryService.getAll();

       //pass the country list obtained with attribute of countries to the UI/user
        model.addAttribute("countries", countries);

       //display the countryList html page i.e(template/setting/countryList)
        return "settings/countryList";
    }

    // add country
    @GetMapping("/countryAdd")
    public String addCountry(){
        return "settings/countryAdd";
    }

    //save new country from form
    @PostMapping("/countries")
    public String save(Country country){
        countryService.save(country);
        return "redirect:/countries";
    }
    //delete country
    @RequestMapping(value = "/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        countryService.delete(id);
        return "redirect:/countries";
    }

    // edit country
    @GetMapping("/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "settings/countryEdit";
    }
    //update country
    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Country country){
        countryService.save(country);
        return "redirect:/countries";
    }

    //country details
    @GetMapping("/countryDetails/{id}")
    public String detailsCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "settings/countryDetails";
    }

}
