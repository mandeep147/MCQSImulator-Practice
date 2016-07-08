import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email extends settings{
	public void sendEmail(String username, String uuid){
		String usernameEmail = email;
		String passwordEmail = passwordSign; 
 
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usernameEmail, passwordEmail);
			}		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mndpkaur14@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(username));
			message.setSubject("Forgot Passowrd");
			message.setText("Copy following code,"
				+ "\n\n to reset password: "+ uuid);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
