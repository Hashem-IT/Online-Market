package de.Hashem.bigbazar.service;

import de.Hashem.bigbazar.entity.Kunde;
import de.Hashem.bigbazar.repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* Klasse für KundeService
*/


@Service
public class KundeService implements KundeServiceIF {

    @Autowired
    private KundeRepository kundeRepository;

    @Override
    public Kunde kundeAnlegen(Kunde kunde) {
        Kunde neu = kundeRepository.save(kunde);
        return neu;
    }
}