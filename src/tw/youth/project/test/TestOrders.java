package tw.youth.project.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Orders;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class TestOrders {

	@Test
	public void test() {
		DBManager dao = new DBManager("jdbc:mysql://localhost:3306/" + SQLCmd.DB, "odise", "116025");
		dao.starup();

		System.out.println("取得使用者");
		AUSER user = new Login(dao, "odise1", "116025").getUser();
		AEMP aemp = new AEMP();
		ADEP adep0 = new ADEP();
		System.out.println("取得使用者基本資料");
		ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], user.getEmpno(), aemp.getLength());
		for (Object[] objects : arr) {
			aemp.setValuesFull(objects);
		}
		user.setDno(aemp.getDno());
		user.setFno(aemp.getFno());
		ArrayList<Object[]> arr2 = dao.query(adep0.getTableName(), adep0.getKeys()[1], user.getDno(),
				adep0.getLength());
		for (Object[] objects : arr2) {
			adep0.setValuesFull(objects);
		}
		user.setRole(adep0.getRole());
		Orders orders = new Orders(dao);

		// 建立訂單
		System.out.println("建立訂單");

		Object[] priObjs = { "0", ToolBox.getCurrentTimestamp(), user.getEmpno(), user.getFno(), user.getDno(), 7.0f,
				"Preparing", 4, "測試申請單1" };
		AODR aodr = new AODR();
		aodr.setValues(priObjs);

		// 建立副訂單
		System.out.println("建立副訂單");

		Object[] secObjs = { "訂單編號", "中原大學", "電機系院長", 4, "S001-F1", 1, 880.0f, "洽談產學合作-高爾夫球組一廠", 1 };
		AODRDT[] aodrdts = new AODRDT[3];
		AODRDT aodrdt = new AODRDT();
		aodrdt.setValues(secObjs);
		aodrdts[0] = aodrdt;
		aodrdt = new AODRDT();
		secObjs[4] = "S001-F2";
		aodrdt.setValues(secObjs);
		aodrdts[1] = aodrdt;
		aodrdt = new AODRDT();
		secObjs[4] = "S001-F3";
		aodrdt.setValues(secObjs);
		aodrdts[2] = aodrdt;

		// 發送訂單建立請求
		System.out.println("發送訂單建立請求");
		System.out.println(orders.createOrders(dao, user, aodr, aodrdts));
		System.out.println("發送訂單成功");

		// 建立調撥單
		System.out.println("建立調撥單");

		priObjs = new Object[] { "調撥單編號", "F1", ToolBox.getCurrentTimestamp(),
				ToolBox.strToSqlTimestamp("2016-12-01 12:00:00"), "F2", "T", 20.0f, "調撥單測試一" };
		AIO aio = new AIO();
		aio.setValues(priObjs);

		// 建立調撥單副檔
		System.out.println("建立調撥單副檔");

		secObjs = new Object[] { "調撥單編號", "E001-F1", "E001-F2", 10, 40.0f, "A201611002", "從一廠調撥10個橘色鍵盤刷到二廠" };
		AIODT[] aiodts = new AIODT[3];
		AIODT aiodt = new AIODT();
		
		aiodt.setValues(secObjs);
		aiodts[0] = aiodt;
		aiodt = new AIODT();
		secObjs[4] = 20.0f;
		aiodt.setValues(secObjs);
		aiodts[1] = aiodt;
		aiodt = new AIODT();
		secObjs[4] = 30.0f;
		aiodt.setValues(secObjs);
		aiodts[2] = aiodt;

		// 發送調撥單建立請求
		System.out.println("發送調撥單建立請求");
		System.out.println(orders.createOrders(dao, user, aio, aiodts));
		System.out.println("發送調撥單成功");

		// 修改訂單

	}

}
