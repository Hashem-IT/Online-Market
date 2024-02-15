package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.Kunde;
import org.springframework.data.repository.CrudRepository;

/*
* Klasse für KundeRepository
*/


public interface KundeRepository extends CrudRepository<Kunde, Long> {
}

