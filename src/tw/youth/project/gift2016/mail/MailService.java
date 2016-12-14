package tw.youth.project.gift2016.mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class MailService {

	public void sendMail(String to, String subject, String messageText) {
		try {
			String host = "smtp.gmail.com";
			int port = 587;
			final String username = "odise7380@gmail.com";
			final String password = "0okm9ijn";

			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", port);
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));// 這裡是寄件人
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));// 這裡可以改用你的email帳號來做為收件人
			message.setSubject(subject);
			message.setText(messageText);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);
			Transport.send(message);
			System.out.println("寄送email-888結束.");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
