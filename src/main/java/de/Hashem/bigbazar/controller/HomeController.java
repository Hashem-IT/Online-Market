package de.Hashem.bigbazar.controller;

import de.Hashem.bigbazar.entity.Customer;
import de.Hashem.bigbazar.entity.Kunde;
import de.Hashem.bigbazar.service.CustomerServiceIF;
import de.Hashem.bigbazar.service.KundeServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/*
*
* Klasse für erste Seite , wo Anmelden und Registrieren gibt 
*/

@Controller
public class HomeController {

    @Autowired
    private KundeServiceIF kundeService;
 
    @Autowired
    private CustomerServiceIF customerService;

    //erste Seite
    @RequestMapping("/")
    public String starten() {
        return "index";
    }

    //Anmenlden
    @RequestMapping("/login")
    public String loginPage() { return "login";

    }

 
    //Registrieren-Seite anrufen
    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String register(Model model) {
        model.addAttribute("newCustomer", new Customer());
        return "register";
    }

    //Registrieren
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public String addCustomer(@ModelAttribute("newCustomer") Customer customer) {
        customerService.createNewCustomer(customer);
        customerService.createShoppingCartForCustomer(customer);
        return "redirect:/login";
    }
 
    //Registrieren
    @RequestMapping("/registrieren")
    public String registrieren(
            @ModelAttribute("vorname") String vname,
            @ModelAttribute("nachname") String nname,
            Model model
    ) {
        Kunde kunde = new Kunde();
        kunde.setVorname(vname);
        kunde.setNachname(nname);
        kunde = kundeService.kundeAnlegen(kunde);
        model.addAttribute("kundennr", kunde.getKundenNr());

        return "kundenkonto";
    }

}