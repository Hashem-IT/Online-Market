package de.Hashem.bigbazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.Hashem.bigbazar.entity.Customer;

/*
* Klasse für UserRepo für GET POST MAPPING
*/

public interface UserRepo extends JpaRepository<Customer,Long> {

}
