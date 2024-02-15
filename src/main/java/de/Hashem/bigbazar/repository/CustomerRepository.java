package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

/*
* Klasse für CustomerRepository
*/

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE email LIKE :email")
    Optional<Customer> findByEmail(@Param("email") String email);

}
