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
		DBManager dao = new DBManager(SQLCmd.DB_URL , SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();
		System.out.println("開始登入功能");
		Login login = new Login(dao, "P0001", "P0001");
		
		System.out.println("測試驗證登入");
		System.out.println(login.checkLogin());
		
		System.out.println("測試修改密碼 : 舊密碼不正確範例");
		System.out.println(login.changPassword(dao, login.getUser(),"P0000", "P0002", "P0002"));
		System.out.println("測試修改密碼 : 新密碼(1)不正確範例");
		System.out.println(login.changPassword(dao, login.getUser(),"P0001", "P0001", "P0002"));
		System.out.println("測試修改密碼 : 新密碼(2)不正確範例");
		System.out.println(login.changPassword(dao, login.getUser(),"P0001", "P0002", "P0001"));
		System.out.println("測試修改密碼 : 新舊密碼都正確範例");
		System.out.println(login.changPassword(dao, login.getUser(),"P0001", "P0002", "P0002"));
		System.out.println("測試修改密碼 : 測試完畢，利用修改密碼方式來恢復成原密碼");
		System.out.println(login.changPassword(dao, login.getUser(),"P0002", "P0001", "P0001") + "\n");

		System.out.println("測試發送電子郵件:不存在的電子郵件");
		System.out.println(login.forgotPass(dao, "p0025@alaya.com","www.google.com")[0]);
		System.out.println("測試發送電子郵件:已存在的電子郵件");
		System.out.println(login.forgotPass(dao, "p0024@alaya.com","www.google.com")[0]);
	}

}
