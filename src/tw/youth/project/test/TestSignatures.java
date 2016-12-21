package tw.youth.project.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Signatures;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
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
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno()+ " ; " + aodr.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());

		user = new Login(manager, "P0003", "P0003").getUser();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno()+ " ; " + aodr.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());

		user = new Login(manager, "P0002", "P0002").getUser();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno()+ " ; " + aodr.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());

		System.out.println("指定完成簽核者");
		user = new Login(manager, "P0016", "P0016").getUser();
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno()+ " ; " + aodr.getStatus());
		System.out.println("同意 : " + signatures.completeOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());
		APRESENT apresent = new APRESENT();
		AODRDT aodrdt = new AODRDT();
		for (Object[] objs : manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], "A201612023",
				aodrdt.getLength())) {
			aodrdt.setValuesFull(objs);
			apresent.setValuesFull(manager
					.query(apresent.getTableName(), apresent.getKeys()[1], aodrdt.getFgno(), apresent.getLength())
					.get(0));
			aodrdt = new AODRDT();
		}

		System.out.println("作廢訂單");
		user = new Login(manager, "P0006", "P0006").getUser();
		System.out.println("檢查 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno()+ " ; " + aodr.getStatus());
		System.out.println("同意 : " + signatures.obsoleteOrder(manager, user, aodr));
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());
		apresent = new APRESENT();
		aodrdt = new AODRDT();
		for (Object[] objs : manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], "A201612023",
				aodrdt.getLength())) {
			aodrdt.setValuesFull(objs);
			apresent.setValuesFull(manager
					.query(apresent.getTableName(), apresent.getKeys()[1], aodrdt.getFgno(), apresent.getLength())
					.get(0));
			aodrdt = new AODRDT();
		}

		System.out.println("復原");
		aodr.setStatus(ConstValue.ORDERS_STATUS_PROCESSING);
		aodr.setSignerno("P0006");
		manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull());
		ASIGNLOG asignlog = new ASIGNLOG();
		for (Object[] objs : manager.query(asignlog.getTableName(), asignlog.getKeys()[1], "A201612023",
				asignlog.getLength())) {
			manager.drop(asignlog.getTableName(), asignlog.getKeys()[0], objs[0]);
		}
		aodr = (AODR) signatures.getOrder(manager, user, aodr.getTableName(), "A201612023");
		System.out.println("確認 : " + aodr.getSignerlist() + " ; " + aodr.getSignerno() + " ; " + aodr.getStatus());
		System.out.println("執行簽核狀態復原");
		asignlog = new ASIGNLOG();
			asignlog.setValues(new Object[] { aodr.getOrder1(), user.getEmpno(), user.getEname(), user.getDno(),
					0.0f, "Send" });
			manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues());
		System.out.println("復原完成\n");

		// 取得單筆調撥單
		System.out.println("取得單筆調撥單");
		AIO aio = new AIO();
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("檢查 : " + aio.getSignerlist() + " ; " + aio.getSignerno()+ " ; " + aio.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aio));
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());

		user = new Login(manager, "P0003", "P0003").getUser();
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("檢查 : " + aio.getSignerlist() + " ; " + aio.getSignerno()+ " ; " + aio.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aio));
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());

		user = new Login(manager, "P0002", "P0002").getUser();
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("檢查 : " + aio.getSignerlist() + " ; " + aio.getSignerno()+ " ; " + aio.getStatus());
		System.out.println("同意 : " + signatures.approveOrder(manager, user, aio));
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());

		System.out.println("指定完成簽核者");
		user = new Login(manager, "P0016", "P0016").getUser();
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("檢查 : " + aio.getSignerlist() + " ; " + aio.getSignerno()+ " ; " + aio.getStatus());
		System.out.println("同意 : " + signatures.completeOrder(manager, user, aio));
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());
		apresent = new APRESENT();
		AIODT aiodt = new AIODT();
		for (Object[] objs : manager.query(aiodt.getTableName(), aiodt.getKeys()[1], "B201612027",
				aiodt.getLength())) {
			aiodt.setValuesFull(objs);
			apresent.setValuesFull(manager
					.query(apresent.getTableName(), apresent.getKeys()[1], aiodt.getOutno(), apresent.getLength())
					.get(0));
			aiodt = new AIODT();
		}

		System.out.println("作廢調撥單");
		user = new Login(manager, "P0006", "P0006").getUser();
		System.out.println("檢查 : " + aio.getSignerlist() + " ; " + aio.getSignerno()+ " ; " + aio.getStatus());
		System.out.println("同意 : " + signatures.obsoleteOrder(manager, user, aio));
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());
		apresent = new APRESENT();
		aiodt = new AIODT();
		for (Object[] objs : manager.query(aiodt.getTableName(), aiodt.getKeys()[1], "B201612027",
				aiodt.getLength())) {
			aiodt.setValuesFull(objs);
			apresent.setValuesFull(manager
					.query(apresent.getTableName(), apresent.getKeys()[1], aiodt.getOutno(), apresent.getLength())
					.get(0));
			aiodt = new AIODT();
		}

		System.out.println("復原");
		aio.setStatus(ConstValue.ORDERS_STATUS_PROCESSING);
		aio.setSignerno("P0006");
		manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull());
		asignlog = new ASIGNLOG();
		for (Object[] objs : manager.query(asignlog.getTableName(), asignlog.getKeys()[1], "B201612027",
				asignlog.getLength())) {
			manager.drop(asignlog.getTableName(), asignlog.getKeys()[0], objs[0]);
		}
		aio = (AIO) signatures.getOrder(manager, user, aio.getTableName(), "B201612027");
		System.out.println("確認 : " + aio.getSignerlist() + " ; " + aio.getSignerno() + " ; " + aio.getStatus());
		System.out.println("執行簽核狀態復原");
		asignlog = new ASIGNLOG();
			asignlog.setValues(new Object[] { aio.getVhno(), user.getEmpno(), user.getEname(), user.getDno(),
					0.0f, "Send" });
			manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues());
		System.out.println("復原完成\n");
		System.out.println("結束測試");
	}

}
