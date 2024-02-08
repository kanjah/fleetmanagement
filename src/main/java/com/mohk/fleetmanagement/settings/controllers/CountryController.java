package com.mohk.fleetmanagement.settings.controllers;

import com.mohk.fleetmanagement.settings.models.Country;
import com.mohk.fleetmanagement.settings.services.CountryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    // return list of all countries from the service
//    @GetMapping("/settings/countries")
//    //passing the model country to the other pages so that country/countryList can be displayed to the user(UI)
//    //we use keyword to cater for the search in the countryList
//     public String getAll(Model model, String keyword){
//
//        //list of countries from getAll() from CountryService
//      List<Country> countries;
//
//      //check if keyword is present or not
//      if(keyword == null){
//          countries = countryService.findAll();
//      }
//      else {
//          countries = countryService.findByKeyword(keyword);
//      }
//
//       //pass the country list obtained with attribute of countries to the UI/user
//        model.addAttribute("countries", countries);
//
//       //display the countryList html page i.e(template/setting/countryList)
//        return "settings/countryList";
//   }
    //for pagination


   //displays the first page, it's called when the page loads for the fist time
   //The page number argument would be 1.
   @GetMapping("/settings/countries")
   public String getAllPages(Model model){
       return getOnePage(model, 1);
   }


   @GetMapping("/settings/countries/page/{pageNumber}")
   public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage){
       //call the findPage() method, giving it the pageNumber/currentPage as argument.
       Page<Country> page = countryService.findPage(currentPage);

//       once we have the page, we would extract the following:
//
//       Total Pages – total pages in available
//       Total Elements – total elements in the page (it’s possible the last page would have less elements than other pages)
//       Content – the actual list of items. In this case, it’s list of Countries

       int totalPages = page.getTotalPages();
       long totalItems = page.getTotalElements();
       List<Country> countries = page.getContent();

       //send all of this together with the currentPage to the UI as model attributes.
       model.addAttribute("currentPage", currentPage);
       model.addAttribute("totalPages", totalPages);
       model.addAttribute("totalItems", totalItems);
       model.addAttribute("countries", countries);

       return "/settings/countryList";
   }

    // For sorting lists with both Asc and Desc in contryList.html
    @GetMapping("/settings/countries/page/{pageNumber}/{field}")
    public String getPageWithSort(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @PathVariable String field,
                                  @PathParam("sortDir") String sortDir) {

        Page<Country> page = countryService.findAllWithSort(field, sortDir, currentPage);
        List<Country> countries = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);

        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("countries", countries);
        return "/settings/countryList";
    }

    //The Get Country By Id for locationAdd form step 1
    @GetMapping("/settings/country/{id}")
    @ResponseBody //returns json instead of html
    public Country getCountry(@PathVariable Integer id){
        return countryService.getById(id);
    }

    // add country
    @GetMapping("/settings/countryAdd")
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
