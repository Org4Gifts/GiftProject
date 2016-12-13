package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.user.AUSER;

public class CreateUser {

	@Test
	public void test() {
		// fail("Not yet implemented");
		DBManager dao = new DBManager(SQLCmd.DB_URL , SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();

		// USER
		String aempno = "P0016";
		String ausername = "P0016";
		String auserpass = "P0016";
		System.out.println("USER");
		AUSER user = new AUSER();
		Object[] objs = { aempno, ausername, user.toMD5Pass(auserpass) };
		user.setValues(objs);
		System.out.println(dao.insert(user.getTableName(), user.getKeys(), user.getValues()));
	}

}
