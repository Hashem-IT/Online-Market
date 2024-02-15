package de.Hashem.bigbazar.service;

import de.Hashem.bigbazar.entity.*;
import de.Hashem.bigbazar.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import java.util.Optional;

/*
* Klasse für CustomerService
*/

@Service  @Qualifier("labresources")
public class CustomerService implements CustomerServiceIF {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserDetails customer = customerRepository.findByEmail(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("Kunde mit Nr. " + id + " existiert nicht" );});
        return customer;
    }

    // nicht benutzt   
    @Override
    @Transactional
    public void createNewCustomer(Customer customer) {
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        Customer persitedCustomer = customerRepository.save(customer);
     }

    @Override
    @Transactional
    public void removeCustomer(Customer customer){
              customer.setEmail(null);
              customer.setPersonalData(null);
              customer.setPassword(null);
             // funktioniert nicht alle Daten von Kunde zu löschen

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createShoppingCartForCustomer(Customer customer) {
        Customer customer1 = findByEmail(customer.getEmail()).get();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer1);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public void updateCustomer(Customer customer, String email) {
        Customer customerToBeUpdate = customerRepository.findByEmail(email).get();
        customerToBeUpdate.setPersonalData(customer.getPersonalData());
        customerToBeUpdate.setCustomerRoles(customer.getCustomerRoles());
        customerToBeUpdate.getPersonalData().setAddress(customer.getPersonalData().getAddress());
        customerRepository.save(customerToBeUpdate);
    }


}