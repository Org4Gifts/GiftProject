package tw.youth.project.gift2016.mail;

public class TestMailThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Sender("odise7380@gmail.com",
                "有人留言","<html><body>標題:Test Mail-1214"+""+"<br/>內容:"+"Test mail thread from Alaya !"+"</body></html>")).start();
	}			

}
