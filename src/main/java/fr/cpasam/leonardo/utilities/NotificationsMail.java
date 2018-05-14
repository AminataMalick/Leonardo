package fr.cpasam.leonardo.utilities;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificationsMail {
	
	/**
	 * Méthode permettant l'envoi de mail à un destinataire donné
	 * @param subject
	 * @param text
	 * @param destinataire
	 */
	public static void sendMail (String subject, String text, String destinataire) {
		
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	final String username = "leonardo.cpasam@gmail.com";
    final String password = "opopop38400";
    
	  	 // Create and set properties 
	     Properties props = System.getProperties();
	     props.setProperty("mail.smtp.host", "smtp.gmail.com");
	     props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	     props.setProperty("mail.smtp.socketFactory.fallback", "false");
	     props.setProperty("mail.smtp.port", "465");
	     props.setProperty("mail.smtp.socketFactory.port", "465");
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.debug", "true");
	     props.put("mail.store.protocol", "pop3");
	     props.put("mail.transport.protocol", "smtp");
	     
	     
	     try{
	    	 // Create a new session 
		     Session session = Session.getDefaultInstance(props, new Authenticator(){
	             protected PasswordAuthentication getPasswordAuthentication() {
	                 return new PasswordAuthentication(username, password);
	              }});
		                          
	
		     // Create a new message 
		     Message msg = new MimeMessage(session);
	
		     // Set message 
		     msg.setFrom(new InternetAddress("leonardo.cpasam@gmail.com"));
		     msg.setRecipients(Message.RecipientType.TO, 
		                      InternetAddress.parse(destinataire,false));
		     msg.setSubject(subject);
		     msg.setText(text);
		     msg.setSentDate(new Date());
		     Transport.send(msg);
		     System.out.println("Message sent");
		  }catch (MessagingException e){ 
			  System.out.println("Sending error, cause : " + e);}
		  }
	
}
