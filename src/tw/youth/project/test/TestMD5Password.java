package tw.youth.project.test;

import org.junit.Test;
import tw.youth.project.gift2016.sql.user.AUSER;

public class TestMD5Password {
	private AUSER user;
	@Test
	public void createMD5Pass() {
		user = new AUSER();
		String source = "odise";
		System.out.println(user.toMD5Pass(source));
	}
}
