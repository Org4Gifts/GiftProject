package tw.youth.project.test;

import org.junit.Test;
import tw.youth.project.gift2016.mail.*;

public class TestJavaMail {

	//@Test
	public void testMail() {
		String to = "odise7380@gmail.com";
        String subject = "Test Java Mail Test 1214-1";
        String messageText = "Hi! This is a test mail sent from Alaya." + "\n"; 
        MailService mailService = new MailService();
        System.out.println("寄送email-889中,請稍後...");
        mailService.sendMail(to, subject, messageText);
	}
		
	@Test
	public void testMailThread() {
		(new Sender("odise7380@gmail.com",
				 "有人留言","<html><body>標題:Test JavaMail Unit-1214"+""+"<br/>內容:"+"Test mail thread from Alaya !"+"</body></html>")).start();	}		
}
