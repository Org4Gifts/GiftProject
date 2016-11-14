package tw.youth.project.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Query;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class TestQuery {

	@Test
	public void test() {
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		System.out.println("取得使用者");
		AUSER user = new Login(dao, "odise2", "116025").getUser();
		AEMP aemp = new AEMP();
		System.out.println("取得使用者基本資料");
		ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], user.getEmpno(), aemp.getLength());
		for (Object[] objects : arr) {
			aemp.setValuesFull(objects);
		}

		System.out.println("查詢啟動");
		Query query = new Query(user);

		System.out.println("管理者查詢:使用者");
		user = query.getUser(dao, "odise");
		System.out.println(user.getUser());
		System.out.println("管理者查詢:全部使用者");
		ArrayList<AUSER> users = query.getUsers(dao);
		for (AUSER u : users) {
			System.out.println(u.getUser());
		}

		aemp = new AEMP();
		System.out.println("\n" + "管理者查詢:員工的基本資料");
		aemp = query.getAemp(dao, user.getEmpno());
		System.out.println(aemp.getEmail());
		System.out.println("\n" + "管理者查詢:全部員工的基本資料");
		ArrayList<AEMP> aemps = query.getAemps(dao);
		for (AEMP aemp2 : aemps) {
			System.out.println(aemp2.getEmail());
		}

	}

}
