package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;

public class TestLogin {

	@Test
	public void test() {
		// fail("Not yet implemented");
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();
		System.out.println("開始登入功能");
		Login login = new Login(dao, "P0001", "P0001");
		
		System.out.println("測試驗證登入");
		System.out.println(login.checkLogin());
		
		System.out.println("測試修改密碼 : 舊密碼不正確範例");
		System.out.println(login.changPassword(dao, "116021", "116025", "116025"));
		System.out.println("測試修改密碼 : 新密碼(1)不正確範例");
		System.out.println(login.changPassword(dao, "116022", "116024", "116025"));
		System.out.println("測試修改密碼 : 新密碼(2)不正確範例");
		System.out.println(login.changPassword(dao, "116022", "116025", "116024"));
		System.out.println("測試修改密碼 : 新舊密碼都正確範例");
		System.out.println(login.changPassword(dao, "116022", "116025", "116025"));
		System.out.println("測試修改密碼 : 測試完畢，利用修改密碼方式來恢復成原密碼");
		System.out.println(login.changPassword(dao, "116025", "116022", "116022") + "\n");

		System.out.println("測試發送電子郵件:不存在的電子郵件");
		System.out.println(login.forgotPass(dao, "123@com"));
		System.out.println("測試發送電子郵件:已存在的電子郵件");
		System.out.println(login.forgotPass(dao, "456@com"));
	}

}
