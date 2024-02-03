package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Contact;
import com.mohk.fleetmanagement.settings.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/settings/contacts")
    public String getAll(Model model){
        List<Contact> contacts =   contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "/settings/contacts";
    }

    //The Get Contact By Id
    @GetMapping("/settings/contact/{id}")
    @ResponseBody
    public Contact getContact(@PathVariable Integer id){
        return contactService.findById(id);
    }

    @GetMapping("/settings/contactAdd")
    public String addContact(){
        return "/settings/contactAdd";
    }

    //The op parameter is either Edit or Details
    @GetMapping("/settings/contact/{op}/{id}")
    public String editContact(@PathVariable Integer id, @PathVariable String op, Model model){
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "/settings/contact"+ op;
    }

    @PostMapping("/settings/contacts")
    public String save(Contact contact){
        contactService.save(contact);
        return "redirect:/settings/contacts";
    }

    @RequestMapping(value = "/settings/contacts/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public  String delete(@PathVariable Integer id){
        contactService.delete(id);
        return "redirect:/settings/contacts";
    }
}
