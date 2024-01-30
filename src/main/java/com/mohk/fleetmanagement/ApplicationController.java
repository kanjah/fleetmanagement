package com.mohk.fleetmanagement;

import org.springframework.web.bind.annotation.GetMapping;

public class ApplicationController {
    @GetMapping("/index")
    public String home(){
        return "index";
    }
}
