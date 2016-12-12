package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Signatures;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.user.AUSER;

public class TestSignatures {

	@Test
	public void test() {
		DBManager manager = new DBManager(SQLCmd.DB_URL, SQLCmd.DB_NAME, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		manager.starup();

		// 取得必要之資料
		AUSER user = new Login(manager, "P0006", "P0006").getUser();

		Signatures signatures = new Signatures();
		// 取得單筆訂單
		System.out.println("取得單筆訂單");
		AODR aodr = new AODR();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");

		user = new Login(manager, "P0003", "P0003").getUser();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		
		user = new Login(manager, "P0002", "P0002").getUser();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");

		// 取得單筆調撥單
		System.out.println("取得單筆調撥單");

	}

}
