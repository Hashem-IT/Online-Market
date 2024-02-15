
package de.Hashem.bigbazar.Configuration;

import de.Hashem.bigbazar.entity.*;
import de.Hashem.bigbazar.service.CustomerServiceIF;
import de.Hashem.bigbazar.service.ProductServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Klasse für erstellen eine Daten und Produkten als Beispiel
 * und auch eine Konto-Beispiel
*/

@Configuration
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    CustomerServiceIF customerService;

    @Autowired
    ProductServiceIF productService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (productService.findAll().isEmpty()) {
            Customer bigBazar = createDefaultCustomer();
            Customer edenbank = createEdenBankCustomer();
            customerService.createNewCustomer(bigBazar);
            customerService.createNewCustomer(edenbank);
            customerService.createShoppingCartForCustomer(bigBazar);
            customerService.createShoppingCartForCustomer(edenbank);
            productService.saveAll(createDefaultProducts(bigBazar));

        }

    }

    private Customer createEdenBankCustomer() {
        Customer edenbank = createDefaultCustomer();
        edenbank.setEmail("edenbank@edenbank.com");
        edenbank.setPassword("edenbank");
        edenbank.getPersonalData().setIban("DE750300110000000002");
        edenbank.getPersonalData().setBankAccountId(1L);
        edenbank.getPersonalData().setBankPassword("Marco");
        return edenbank;
    }

    private Customer createDefaultCustomer() {
        Customer customer = new Customer();
        customer.setEmail("bigbazar@bigbazar.com");
        customer.setPassword("password");
        PersonalData personalData  = new PersonalData();
        personalData.setBirthdate(new Date());
        personalData.setGender(Gender.DIVERSE);
        personalData.setLastName("Bigbazar");
        personalData.setFirstName("Max");
        personalData.setIban("DE750300110000000006");
        personalData.setBankAccountId(5L);
        personalData.setBankPassword("Hashem");
        Address address = new Address();
        address.setCountry("Germany");
        address.setPostalNumber("93049");
        address.setHouseNumber("55");
        address.setStreetName("Bspstr");
        personalData.setAddress(address);
        customer.setPersonalData(personalData);
        return customer;
    }

    private List<Product> createDefaultProducts(Customer customer) throws IOException {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Product tempProduct = new Product();
            tempProduct.setDescription("testDescription" + i);
            tempProduct.setHeightInMM(new BigDecimal(i * 100));
            //tempProduct.setKathegory(new K);
            tempProduct.setLengthInMM(new BigDecimal(i *100));
            tempProduct.setName("TestProduct" + i);
            tempProduct.setNetto(new BigDecimal(i));
            tempProduct.setBrutto(new BigDecimal(i));
            tempProduct.setTax(BigDecimal.ZERO);
            tempProduct.setWeightInGram(new BigDecimal(i * 100));
            tempProduct.setWidthInMM(new BigDecimal(i * 100));

            tempProduct.setPicture(Files.readAllBytes(Paths.get("H:\\OTH\\SW-Hashem\\bigbazar-master1\\bigbazar-master\\src\\main\\resources\\img\\photography-analog-camera-product-50924.jpg", new String[]{})));
            //tempProduct.setPicture(getClass().getResourceAsStream("/img/photography-analog-camera-product-50924.jpg").readAllBytes());
            tempProduct.setCount(i);
            tempProduct.setOfferdBy(customer);
            products.add(tempProduct);
        }
        for (int i = 1; i < 10; i++) {
            Product tempProduct = new Product();
            tempProduct.setDescription("test" + i);
            tempProduct.setHeightInMM(new BigDecimal(i * 100));
            //kathegory
            tempProduct.setLengthInMM(new BigDecimal(i *100));
            tempProduct.setName("Test" + i);
            tempProduct.setNetto(new BigDecimal(i));
            tempProduct.setBrutto(new BigDecimal(i));
            tempProduct.setTax(BigDecimal.ZERO);
            tempProduct.setWeightInGram(new BigDecimal(i * 100));
            tempProduct.setWidthInMM(new BigDecimal(i * 100));

           // tempProduct.setPicture(Files.readAllBytes(Paths.get("H:\\OTH\\SW-Hashem\\bigbazar-master1\\bigbazar-master\\src\\main\\resources\\img\\photography-analog-camera-product-50924.jpg", new String[]{})));
            tempProduct.setPicture(getClass().getResourceAsStream("/img/photography-analog-camera-product-50924.jpg").readAllBytes());
            tempProduct.setCount(i);
            tempProduct.setOfferdBy(customer);
            products.add(tempProduct);
        }
        for (int i = 1; i < 10; i++) {
            Product tempProduct = new Product();
            tempProduct.setDescription("Description" + i);
            tempProduct.setHeightInMM(new BigDecimal(i * 100));
            //kathegory
            tempProduct.setLengthInMM(new BigDecimal(i *100));
            tempProduct.setName("Product" + i);
            tempProduct.setNetto(new BigDecimal(i));
            tempProduct.setBrutto(new BigDecimal(i));
            tempProduct.setTax(BigDecimal.ZERO);
            tempProduct.setWeightInGram(new BigDecimal(i * 100));
            tempProduct.setWidthInMM(new BigDecimal(i * 100));

            //tempProduct.setPicture(Files.readAllBytes(Paths.get("H:\\OTH\\SW-Hashem\\bigbazar-master1\\bigbazar-master\\src\\main\\resources\\img\\photography-analog-camera-product-50924.jpg", new String[]{})));
            tempProduct.setPicture(getClass().getResourceAsStream("/img/photography-analog-camera-product-50924.jpg").readAllBytes());
            tempProduct.setCount(i);
            tempProduct.setOfferdBy(customer);
            products.add(tempProduct);
        }
        for (int i = 1; i < 10; i++) {
            Product tempProduct = new Product();
            tempProduct.setDescription("BeispielDescription" + i);
            tempProduct.setHeightInMM(new BigDecimal(i * 100));
            //kathegory
            tempProduct.setLengthInMM(new BigDecimal(i *100));
            tempProduct.setName("BeispielProduct" + i);
            tempProduct.setNetto(new BigDecimal(i));
            tempProduct.setBrutto(new BigDecimal(i));
            tempProduct.setTax(BigDecimal.ZERO);
            tempProduct.setWeightInGram(new BigDecimal(i * 100));
            tempProduct.setWidthInMM(new BigDecimal(i * 100));

            //tempProduct.setPicture(Files.readAllBytes(Paths.get("H:\\OTH\\SW-Hashem\\bigbazar-master1\\bigbazar-master\\src\\main\\resources\\img\\photography-analog-camera-product-50924.jpg", new String[]{})));
            tempProduct.setPicture(getClass().getResourceAsStream("/img/photography-analog-camera-product-50924.jpg").readAllBytes());
            tempProduct.setCount(i);
            tempProduct.setOfferdBy(customer);
            products.add(tempProduct);
        }

        return products;
    }
}

