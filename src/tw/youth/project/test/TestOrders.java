package tw.youth.project.test;

import java.util.ArrayList;
import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Orders;
import tw.youth.project.gift2016.func.Querys;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.aodr.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class TestOrders {

	@Test
	public void test() {
		DBManager dao = new DBManager(SQLCmd.DB_URL + SQLCmd.DB, SQLCmd.DB_USER, SQLCmd.DB_PASS);
		dao.starup();

		System.out.println("取得使用者");
		AUSER user = new Login(dao, "P0001", "P0001").getUser();
		AEMP aemp = new AEMP();
		ADEP adep0 = new ADEP();
		System.out.println("取得使用者基本資料");
		ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], user.getEmpno(), aemp.getLength());
		for (Object[] objects : arr) {
			aemp.setValuesFull(objects);
		}
		user.setDno(aemp.getDno());
		user.setFno(aemp.getFno());
		user.setEname(aemp.getEname());
		user.setMgr(aemp.getMgr());
		ArrayList<Object[]> arr2 = dao.query(adep0.getTableName(), adep0.getKeys()[1], user.getDno(),
				adep0.getLength());
		for (Object[] objects : arr2) {
			adep0.setValuesFull(objects);
		}
		user.setRole(adep0.getRole());
		Orders orders = new Orders(dao, user);

		// 建立訂單
		System.out.println("建立訂單");
		Object[] priObjs = { "0", ToolBox.getCurrentTimestamp(), user.getEmpno(), user.getFno(), user.getDno(), 7.0f,
				"Preparing", 4, user.getMgr(), "測試申請單1" };
		AODR aodr = new AODR();
		aodr.setValues(priObjs);

		// System.out.println("建立簽核名單");
		// ArrayList<ASIGNLOG> asignlogs = new ArrayList<>();
		// ASIGNLOG asignlog = new ASIGNLOG();
		// asignlog.setEmpno(user.getEmpno());
		// asignlog.setSignature(user.getEname());
		// asignlog.setDno(user.getDno());
		// asignlogs.add(asignlog);
		// for (AEMP a : orders.getSignatures(dao, user)) {
		// asignlog = new ASIGNLOG();
		// asignlog.setEmpno(a.getEmpno());
		// asignlog.setSignature(a.getEname());
		// asignlog.setDno(a.getDno());
		// asignlogs.add(asignlog);
		// }

		System.out.println("建立副訂單");
		Object[] secObjs = { "訂單編號", "中原大學", "電機系院長", 4, "S001-F1", 1, 880.0f, "洽談產學合作-高爾夫球組一廠", 1 };
		ArrayList<AODRDT> aodrdts = new ArrayList<>();
		AODRDT aodrdt = new AODRDT();
		aodrdt.setValues(secObjs);
		aodrdts.add(aodrdt);
		aodrdt = new AODRDT();
		secObjs[4] = "S001-F2";
		aodrdt.setValues(secObjs);
		aodrdts.add(aodrdt);
		aodrdt = new AODRDT();
		secObjs[4] = "S001-F3";
		aodrdt.setValues(secObjs);
		aodrdts.add(aodrdt);

		System.out.println("發送訂單建立請求");
		System.out.println(orders.createOrders(dao, user, aodr, aodrdts));
		System.out.println("發送訂單成功");

		// 建立調撥單
		System.out.println("建立調撥單");
		priObjs = new Object[] { "調撥單編號", user.getEmpno(), user.getDno(), user.getFno(), ToolBox.getCurrentTimestamp(),
				ToolBox.strToSqlTimestamp("2016-12-01 12:00:00"), "F2", "D", 20.0f, "Preparing", 1, user.getMgr(),
				"調撥單測試一" };
		AIO aio = new AIO();
		aio.setValues(priObjs);

		System.out.println("建立調撥單副檔");
		secObjs = new Object[] { "調撥單編號", "E001-F1", "E001-F2", 10, 40.0f, "A201612002", "從一廠調撥10個橘色鍵盤刷到二廠" };
		ArrayList<AIODT> aiodts = new ArrayList<>();
		AIODT aiodt = new AIODT();
		aiodt.setValues(secObjs);
		aiodts.add(aiodt);
		aiodt = new AIODT();
		secObjs[4] = 20.0f;
		aiodt.setValues(secObjs);
		aiodts.add(aiodt);
		aiodt = new AIODT();
		secObjs[4] = 30.0f;
		aiodt.setValues(secObjs);
		aiodts.add(aiodt);

		System.out.println("發送調撥單建立請求");
		System.out.println(orders.createOrders(dao, user, aio, aiodts));
		System.out.println("發送調撥單成功");

		// 修改訂單
		System.out.println("修改訂單");
		Querys query = new Querys(user);
		aodr = query.getAodrs(dao).get(0);
		aodr.setTamt(100.0f);
		aodrdts = query.getAodrdts(dao, aodr.getOrder1());
		for (AODRDT aodrdt2 : aodrdts) {
			aodrdt2.setQty(40);
		}

		System.out.println("修改訂單之新增禮品");
		secObjs = new Object[] { aodr.getOrder1(), "中原大學", "電機系院長", 4, "S001-F4", 1, 880.0f, "洽談產學合作-高爾夫球組一廠", 1 };
		aodrdt = new AODRDT();
		aodrdt.setValues(secObjs);
		aodrdts.add(aodrdt);

		System.out.println("發送修改訂單");
		System.out.println(orders.updateOrders(dao, user, aodr, aodrdts));
		System.out.println("發送修改訂單完成");

		// 修改調撥單
		System.out.println("修改調撥單");
		aio = query.getAios(dao, aio.getKeys()[1], "").get(0);
		System.out.println(aio.getVhno());
		aio.setTamt(100.0f);
		aiodts = query.getAiodts(dao, aio.getVhno());
		for (AIODT aiodt2 : aiodts) {
			aiodt2.setQty(40);
		}

		System.out.println("修改調撥單之新增禮品");
		secObjs = new Object[] { aio.getVhno(), "E001-F1", "E001-F2", 10, 40.0f, "A201611002", "從一廠調撥10個橘色鍵盤刷到二廠" };
		aiodt = new AIODT();
		aiodt.setValues(secObjs);
		aiodts.add(aiodt);

		System.out.println("發送修改調撥單");
		System.out.println(orders.updateOrders(dao, user, aio, aiodts));
		System.out.println("發送修改調撥單完成");

		// 送出訂單
		System.out.println("送出訂單");
		System.out.println(orders.submitOrders(dao, user, aodr));
		System.out.println("送出訂單完成");

		// 送出調撥單
		System.out.println("送出訂單");
		System.out.println(orders.submitOrders(dao, user, aio));
		System.out.println("送出訂單完成");
	}

}
