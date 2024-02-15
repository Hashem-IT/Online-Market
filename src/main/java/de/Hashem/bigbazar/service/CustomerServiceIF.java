package de.Hashem.bigbazar.service;

import de.Hashem.bigbazar.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

/*
* Klasse für CustomerServiceInterFace
*/


public interface CustomerServiceIF extends UserDetailsService {

    public void createShoppingCartForCustomer(Customer customer);

    void createNewCustomer(Customer customer);

    Optional<Customer> findByEmail(String email);

    void updateCustomer(Customer customer, String email);
    
    void removeCustomer(Customer customer); 

}
