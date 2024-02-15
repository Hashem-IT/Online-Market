package de.Hashem.bigbazar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/*
* Main Klasse und Email senden.
* Er gibt Nachricht wenn er rein kommt
*/

@SpringBootApplication
public class BigbazarApplication {


@Autowired
	// Nachricht zum Email senden
	//private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(BigbazarApplication.class, args);
	}

	//Email senden
	@EventListener(ApplicationReadyEvent.class)
	public void triggerMail() throws Exception {
	//	senderService.sendSimpleEmail("xxx@gmail.com","Es gibt eine Anmeldung ","rame hat schon angemeldet und das ist ein Email zu bestätigen");
	}
}
