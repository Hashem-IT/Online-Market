package de.Hashem.bigbazar.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * Klasse für Sicherheit über Passwort und verschlüsselung
*/

@Configuration
public class MySecuritiyUtilities {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
