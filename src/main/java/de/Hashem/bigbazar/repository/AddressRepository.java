package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Address;
import org.springframework.data.repository.CrudRepository;

/*
* Klasse für AddressRepository
*/

public interface AddressRepository extends CrudRepository<Address, Long> {
}
