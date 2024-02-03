package com.mohk.fleetmanagement.settings.controllers;


import com.mohk.fleetmanagement.settings.models.State;
import com.mohk.fleetmanagement.settings.services.CountryService;
import com.mohk.fleetmanagement.settings.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StateController {
    @Autowired private StateService stateService;
    @Autowired private CountryService countryService;

    public  Model addModelAttribute(Model model){
        model.addAttribute("states", stateService.findAll());
        model.addAttribute("countries", countryService.findAll());
        return model;
    }

    //Get All States
    @GetMapping("/settings/states")
    public String findAll(Model model){
        addModelAttribute(model);
        return "/settings/states";
    }

    @GetMapping("/settings/stateAdd")
    public String addState(Model model){
        addModelAttribute(model);
        return "/settings/stateAdd";
    }

    @GetMapping("/settings/state/{op}/{id}")
    public String editState(@PathVariable Integer id, @PathVariable String op, Model model){
        addModelAttribute(model);
        model.addAttribute("state", stateService.findById(id));
        return "/settings/state" + op;
    }

    //Add State
    @PostMapping(value="/settings/states")
    public String addNew(State state) {
        stateService.save(state);
        return "redirect:/settings/states";
    }

    @RequestMapping(value="/settings/states/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable Integer id) {
        stateService.delete(id);
        return "redirect:/settings/states";
    }

}