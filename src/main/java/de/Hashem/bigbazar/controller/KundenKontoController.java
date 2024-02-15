package de.Hashem.bigbazar.controller;

import de.Hashem.bigbazar.entity.Customer;
import de.Hashem.bigbazar.service.CustomerServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

/*
*
* Klasse für Kunde, Er kann seine Daten änderen und speichern 
*/

@Controller
public class KundenKontoController {

    @Autowired
    private CustomerServiceIF customerService;

    // Seite anrufen
    @RequestMapping(method = RequestMethod.GET, value = "/kontobearbeiten")
    public String changeCustomer(Model model, Principal principal) {
        Customer customer = customerService.findByEmail(principal.getName()).get();
        model.addAttribute("customer", customer);
        return "kontobearbeiten";
    }

    //Daten änderen und speichern 
    @RequestMapping(method = RequestMethod.POST, value = "/kontobearbeiten")
    public String sumitChangeCustomer(@ModelAttribute("customer") Customer customer, Principal principal) {
        customerService.updateCustomer(customer, principal.getName());
        return "index.html";
    }
}
