package fr.cpasam.leonardo.sendMail;

import fr.cpasam.leonardo.notifications.NotificationsMail;

public class TestSendMail {


	public static void main(String[] args) {
		String destinataire = "celine.potte38@gmail.com" ;
		String sujet = "[LEONARDO] - Coucou" ;
		String text = "Coucou petite perruche" ;

		NotificationsMail.sendMail(sujet, text, destinataire);
	}

}
