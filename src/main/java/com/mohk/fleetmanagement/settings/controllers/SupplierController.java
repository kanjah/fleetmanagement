package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Supplier;
import com.mohk.fleetmanagement.settings.services.CountryService;
import com.mohk.fleetmanagement.settings.services.StateService;
import com.mohk.fleetmanagement.settings.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;
    @Autowired	private CountryService countryService;
    @Autowired	private StateService stateService;

    public Model addModelAttribute(Model model){
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("countries", countryService.findAll());
        model.addAttribute("states", stateService.findAll());
        return model;
    }

    @GetMapping("/settings/suppliers")
    public String findAll(Model model){
        addModelAttribute(model);
        return "/settings/suppliers";
    }

    @GetMapping("/settings/supplierAdd")
    public String addSupplier(Model model){
        model.addAttribute("countries", countryService.findAll());
        return "settings/supplierAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/supplier/{op}/{id}")
    public String editSupplier(@PathVariable Integer id, @PathVariable String op, Model model){
        Supplier supplier = supplierService.findById(id);
        addModelAttribute(model);
        model.addAttribute("supplier", supplier);

        return "/settings/supplier"+ op; //returns supplierEdit or supplierDetails
//    public String editSupplier(@PathVariable Integer id, @PathVariable String op, Model model){
//        addModelAttribute(model);
//        model.addAttribute("supplier", supplierService.findById(id));
//        return "/settings/supplier" + op;
    }

    @PostMapping("/settings/suppliers")
    public String save(Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/settings/suppliers";
    }

    @RequestMapping(value="/settings/suppliers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@PathVariable Integer id) {
        supplierService.deleteById(id);
        return "redirect:/settings/suppliers";
    }
}
