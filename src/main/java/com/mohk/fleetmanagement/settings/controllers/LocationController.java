package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Location;
import com.mohk.fleetmanagement.settings.services.CountryService;
import com.mohk.fleetmanagement.settings.services.LocationService;
import com.mohk.fleetmanagement.settings.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController {
    @Autowired	private LocationService locationService;
    @Autowired	private CountryService countryService;
    @Autowired	private StateService stateService;

    public Model addModelAttribute(Model model){
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return model;
    }

    @GetMapping("/settings/locations")
    public String findAll(Model model){
        addModelAttribute(model);
        return "/settings/locations";
    }

    @GetMapping("/settings/locationAdd")
    public String addLocation(Model model){
        //loads list of countries to locationAdd form
        model.addAttribute("countries", countryService.findAll());
        return "settings/locationAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/location/{op}/{id}")
    public String editLocation(@PathVariable Integer id, @PathVariable String op, Model model){
        Location location = locationService.findById(id);
        model.addAttribute("location", location);
        addModelAttribute(model);
        return "/settings/location"+ op; //returns locationEdit or locationDetails
    }

    @PostMapping("/settings/locations")
    public String save(Location location) {
        locationService.save(location);
        return "redirect:/settings/locations";
    }

    @RequestMapping(value="/settings/locations/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        locationService.delete(id);
        return "redirect:/settings/locations";
    }

}
