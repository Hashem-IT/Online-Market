package de.Hashem.bigbazar.repository;

import de.Hashem.bigbazar.entity.PersonalData;
import org.springframework.data.repository.CrudRepository;

/*
* Klasse für PersonalDataRepository
*/

public interface PersonalDataRepository extends CrudRepository<PersonalData, Long> {
}
