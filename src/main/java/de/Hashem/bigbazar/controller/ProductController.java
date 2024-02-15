package de.Hashem.bigbazar.controller;

import de.Hashem.bigbazar.entity.Product;
import de.Hashem.bigbazar.entity.Customer;
import de.Hashem.bigbazar.entity.ShoppingCart;
import de.Hashem.bigbazar.service.CustomerServiceIF;
import de.Hashem.bigbazar.service.ProductServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;

/*
*
* Klasse für Kunde, Er kann für Produkten suchen und zu Warenkorp addieren ,Mengen änderen,löschen und kaufen 
* auch steht Funktionen für Konto löschen und Proukten einfügen
*/


@Controller
public class ProductController {

    @Autowired
    ProductServiceIF productService;
    
    @Autowired
    CustomerServiceIF customerService;

    // Produkten suchen
    @RequestMapping(method = RequestMethod.POST, value = "/productsearch")
    public ModelAndView searchProducht(@RequestParam(value = "searchterm", required = false, defaultValue = "") String searchterm) {
        System.out.println(searchterm);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productsearch");
        modelAndView.addObject( "products" ,productService.findByName(searchterm));
        modelAndView.addObject("searchterm", searchterm);


        return modelAndView;
    }

    // in Warenkorp einfügen
    @RequestMapping(value = "/addshoppincart", method = RequestMethod.POST)
    public ModelAndView addToShoopinCart(@RequestParam(value = "productid", required = true) String productid, ModelAndView modelAndView, Principal principal) {
        System.out.println(productid + " erhalten zum produkt addtoShoppincart");
        System.out.println(modelAndView.getModel().get("searchterm"));
        modelAndView.setViewName("forward:/productsearch");
        productService.addProductToShoppingCart(Long.parseLong(productid), principal.getName());
        return modelAndView;
    }

    //  Warenkorp anrufen
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/shoppingcart")
    public ModelAndView getShoppingCart(ModelAndView modelAndView, Principal principal) {
        ShoppingCart shoppingcart = productService.findShoppinCartByEmail(principal.getName());
        modelAndView.addObject("shoppingcart", shoppingcart);
       modelAndView.setViewName("shoppingcart");
   
        return modelAndView;
    }

    // aus Warenkorp löschen
    @RequestMapping(value = "/shoppingcart/remove", method = RequestMethod.POST)
    public ModelAndView removeFromShoppingCart(@RequestParam(value = "productid", required = true) String productid, ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("forward:/shoppingcart");
        productService.removeProductFromShoppingCart(Long.parseLong(productid), principal.getName());
        return modelAndView;
    }


    // aus Warenkorp die Menge änderen
    @RequestMapping(value = "/shoppingcart/change", method = RequestMethod.POST)
    public ModelAndView changeProductInShoppinCart(@RequestParam(value = "productid", required = true) String productid, @RequestParam(value = "amount", required = true) String amaount,ModelAndView modelAndView, Principal principal) {
        modelAndView.setViewName("forward:/shoppingcart");
        productService.changeAmountOfProductInShoppinCart(Long.parseLong(productid), principal.getName(), Integer.valueOf(amaount));
        return modelAndView;
    }

    // Produkten von Warenkorp kaufen
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/buyproducts")
    public ModelAndView getShoppingCar(ModelAndView modelAndView, Principal principal) {
        ShoppingCart shoppingcart = productService.findShoppinCartByEmail(principal.getName());
        modelAndView.addObject("shoppingcart", shoppingcart);
        modelAndView.setViewName("ergebnis");
        return modelAndView;
    }
 
    // Proukten einfügen Seite anrufen
    @RequestMapping(method = RequestMethod.GET, value = "/produktadd")
    public String produktAdd() {
        return "produktadd";
    }

    //Proukten einfügen
    @RequestMapping("/produktadd")
    public ModelAndView registrieren(@RequestParam String name,
    @RequestParam String description,
    @RequestParam BigDecimal heightInMM,
    @RequestParam BigDecimal lengthInMM,
    @RequestParam BigDecimal netto,
    @RequestParam BigDecimal brutto,
    @RequestParam BigDecimal tax,
    @RequestParam BigDecimal weightInGram,
    @RequestParam BigDecimal widthInMM,
    ModelAndView mv
    ) throws IOException {
        Product produkt = new Product();
        produkt.setName(name);
        produkt.setDescription(description);
        produkt.setHeightInMM(heightInMM);
        produkt.setLengthInMM(lengthInMM);
        produkt.setNetto(netto);
        produkt.setBrutto(brutto);
        produkt.setTax(tax);
        produkt.setWeightInGram(weightInGram);
        produkt.setPicture(getClass().getResourceAsStream("/img/photography-analog-camera-product-50924.jpg").readAllBytes());
        produkt.setWidthInMM(widthInMM);
        mv = new ModelAndView();
        System.out.println("getName is..."+produkt.getId());
        System.out.println("getName is..."+produkt.getName());
		System.out.println("getDescription is..."+ produkt.getDescription());
        productService.save(produkt);

        return mv;
    }

    //
    // Konto löschen Seite anrufen
    @RequestMapping(method = RequestMethod.GET, value = "/kontoDelete")
    public String deleteCustomer(Model model, Principal principal) {
        Customer customer = customerService.findByEmail(principal.getName()).get();
        model.addAttribute("customer", customer);
    
    
        return "kontoDelete";
    }

    //
    // Konto löschen 
    @RequestMapping(method = RequestMethod.GET, value = "/kontoDeletee")
    public String deleteCustomerr(Model model, Principal principal) {
        Customer customer = customerService.findByEmail(principal.getName()).get();
        model.addAttribute("customer", customer);
        customerService.removeCustomer(customer);
        return "login.html";
    }
}
