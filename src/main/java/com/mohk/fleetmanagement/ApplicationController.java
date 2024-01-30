package com.mohk.fleetmanagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ApplicationController {
    @GetMapping("/index")
    public String home(){
        return "index";
    }
    @GetMapping("/hr")
    public String hr(){
        return "hr/index";
    }

    @GetMapping("/accounts")
    public String accounts(){
        return "accounts/index";
    }
    @GetMapping("/reports")
    public String reports(){
        return "reports/index";
    }
    @GetMapping("/payroll")
    public String payroll(){
        return "payroll/index";
    }

    @GetMapping("/fleet")
    public String fleet(){
        return "fleet/index";
    }
    @GetMapping("/settings")
    public String settings(){
        return "settings/index";
    }

    @GetMapping("/helpdesk")
    public String helpdesk(){
        return "helpdesk/index";
    }

    @GetMapping("/security")
    public String security(){
        return "security/index";
    }
}
