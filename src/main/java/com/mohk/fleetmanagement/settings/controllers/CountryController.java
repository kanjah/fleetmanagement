package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.services.CountryService;
import jakarta.websocket.server.PathParam;
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
    @GetMapping("/settings/countries")
    //passing the model country to the other pages so that country/countryList can be displayed to the user(UI)
    //we use keyword to cater for the search in the countryList
     public String getAll(Model model, String keyword){

        //list of countries from getAll() from CountryService
      List<Country> countries;

      //check if keyword is present or not
      if(keyword == null){
          countries = countryService.findAll();
      }
      else {
          countries = countryService.findByKeyword(keyword);
      }

       //pass the country list obtained with attribute of countries to the UI/user
        model.addAttribute("countries", countries);

       //display the countryList html page i.e(template/setting/countryList)
        return "settings/countryList";
   }

    // For sorting lists with both Asc and Desc in contryList.html
    @GetMapping("/settings/countries/{field}")
       public String getAllWithSort(Model model,
                                    @PathVariable("field") String field,
                                    @PathParam("sortDir") String sortDir){ //sortDir is found in the countryList table header section

          List<Country> countries;

          countries = countryService.findAllWithSort(field, sortDir);
          model.addAttribute("sortDir", sortDir);
          model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
          model.addAttribute("countries", countries);
          return "settings/countryList";
    }

    //The Get Country By Id for locationAdd form step 1
    @GetMapping("/settings/country/{id}")
    @ResponseBody //returns json instead of html
    public Country getCountry(@PathVariable Integer id){
        return countryService.getById(id);
    }

    // add country
    @GetMapping("/countryAdd")
    public String addCountry(){
        return "settings/countryAdd";
    }

    //save new country from form
    @PostMapping("/settings/countries")
    public String save(Country country){
        countryService.save(country);
        return "redirect:/settings/countries";
    }
    //delete country
    @RequestMapping(value = "settings/countries/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(@PathVariable Integer id){
        countryService.delete(id);
        return "redirect:/settings/countries";
    }

    // edit country
    @GetMapping("/settings/countryEdit/{id}")
    public String editCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "/settings/countryEdit";
    }
    //update country
    @RequestMapping(value = "/countries/update/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(Country country){
        countryService.save(country);
        return "redirect:/settings/countries";
    }

    //country details
    @GetMapping("/settings/countryDetails/{id}")
    public String detailsCountry(@PathVariable Integer id, Model model){
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "settings/countryDetails";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/country/{op}/{id}")
    public String editCountry(@PathVariable Integer id, @PathVariable String op, Model model) {
        Country country = countryService.getById(id);
        model.addAttribute("country", country);
        return "/settings/country" + op;
    }


}
