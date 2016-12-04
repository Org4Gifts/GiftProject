package tw.youth.project.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import tw.youth.project.gift2016.func.Login;
import tw.youth.project.gift2016.func.Querys;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.afab.AFAB;
import tw.youth.project.gift2016.sql.ainventory.AINVENTORY;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.aqty.AQTY;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class TestQuery {
	// 目前測試位置 108行
	@Test
	public void test() {
		DBManager dao = new DBManager(SQLCmd.DB_URL + SQLCmd.DB, SQLCmd.DB_USER, SQLCmd.DB_PASS);
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
		System.out.println(aemp.getDno() + " " + aemp.getFno());
		user.setDno(aemp.getDno());
		user.setFno(aemp.getFno());
		user.setEname(aemp.getEname());
		ArrayList<Object[]> arr2 = dao.query(adep0.getTableName(), adep0.getKeys()[1], user.getDno(),
				adep0.getLength());
		for (Object[] objects : arr2) {
			adep0.setValuesFull(objects);
		}
		System.out.println(adep0.getRole());
		user.setRole(adep0.getRole());

		System.out.println("查詢啟動");
		Querys query = new Querys(user);

		System.out.println("管理者查詢:使用者");
		user = query.getUsers(dao, user.getKeys()[2], "odise").get(0);
		System.out.println(user.getUser());
		System.out.println("管理者查詢:全部使用者");
		ArrayList<AUSER> users = query.getUsers(dao, user.getKeys()[2], "");
		for (AUSER u : users) {
			System.out.println(u.getUser());
		}

		aemp = new AEMP();
		System.out.println("\n" + "管理者查詢:員工的基本資料");
		aemp = query.getAemps(dao, user.getKeys()[1], user.getEmpno()).get(0);
		System.out.println(aemp.getEmail());
		System.out.println("\n" + "管理者查詢:全部員工的基本資料");
		ArrayList<AEMP> aemps = query.getAemps(dao, user.getKeys()[1], "");
		for (AEMP aemp2 : aemps) {
			System.out.println(aemp2.getEmail());
		}

		APRESENT apresent = new APRESENT();
		System.out.println("\n" + "一般查詢:指定禮品");
		apresent = query.getApresents(dao, apresent.getKeys()[1], "S001-F6").get(0);
		System.out.println(apresent.getFgno());
		System.out.println("\n" + "一般查詢:全部禮品");
		ArrayList<APRESENT> apresents = query.getApresents(dao, apresent.getKeys()[1], "");
		for (APRESENT apresent2 : apresents) {
			System.out.println(apresent2.getFgno());
		}

		AFAB afab = new AFAB();
		System.out.println("\n" + "一般查詢:指定廠區");
		afab = query.getAfabs(dao, afab.getKeys()[2], "二廠").get(0);
		System.out.println(afab.getFname());
		System.out.println("\n" + "一般查詢:全部廠區");
		ArrayList<AFAB> afabs = query.getAfabs(dao, afab.getKeys()[2], "");
		for (AFAB afab2 : afabs) {
			System.out.println(afab2.getFname());
		}

		ADEP adep = new ADEP();
		System.out.println("\n" + "一般查詢:指定部門");
		adep = query.getAdeps(dao, adep.getKeys()[1], "0802").get(0);
		System.out.println(adep.getDno());
		System.out.println("\n" + "一般查詢:全部部門");
		ArrayList<ADEP> adeps = query.getAdeps(dao, adep.getKeys()[1], "");
		for (ADEP adep2 : adeps) {
			System.out.println(adep2.getDno());
		}

		// 目前測試位置 108
		// 一般訂單 : 依登入之使用者權限而有不同的查詢結果，一般職員僅能查詢到自己的訂單，課長級可以查到整個部門的訂單
		System.out.println("\n" + "使用者權限查詢:全部訂單、訂單的副檔、訂單的簽核狀態");
		ArrayList<AODR> aodrs = query.getAodrs(dao);
		for (AODR aodr2 : aodrs) {
			System.out.println(aodr2.getOrder1());
			ArrayList<AODRDT> aodrdts = query.getAodrdts(dao, aodr2.getOrder1());
			for (AODRDT aodrdt : aodrdts) {
				System.out.println(aodrdt.getOrder1() + " ; " + "" + aodrdt.getFgno());
			}
			ArrayList<ASIGNLOG> asignlogs = query.getAsignlogs(dao, aodr2.getOrder1());
			for (ASIGNLOG asignlog : asignlogs) {
				System.out.println(asignlog.getOrder1() + " ; " + asignlog.getEmpno());
			}
		}

		// 調撥訂單 : 依管理部門登入之使用者權限而有不同的查詢結果，一般職員僅能查詢到自己的調撥單，課長級可以查到整個部門的調撥單
		System.out.println("\n" + "使用者權限查詢:全部調撥單、調撥單的副檔、調撥單的簽核狀態");
		AIO aio = new AIO();
		ArrayList<AIO> aios = query.getAios(dao, aio.getKeys()[1], "");
		for (AIO aio2 : aios) {
			System.out.println(aio2.getVhno());
			ArrayList<AIODT> aiodts = query.getAiodts(dao, aio2.getVhno());
			for (AIODT aiodt : aiodts) {
				System.out.println(aiodt.getVhno() + " ; " + "" + aiodt.getOrder1());
			}
			ArrayList<ASIGNLOG> asignlogs = query.getAsignlogs(dao, aio2.getVhno());
			for (ASIGNLOG asignlog : asignlogs) {
				System.out.println(asignlog.getOrder1() + " ; " + asignlog.getEmpno());
			}
		}

		AVDR avdr = new AVDR();
		System.out.println("\n" + "核銷部門以上權限查詢:指定廠商");
		avdr = query.getAvdrs(dao, avdr.getKeys()[4], "42610659").get(0);
		System.out.println(avdr.getId());
		System.out.println("\n" + "核銷部門以上權限查詢:全部廠商");
		ArrayList<AVDR> avdrs = query.getAvdrs(dao, avdr.getKeys()[4], "");
		for (AVDR avdr2 : avdrs) {
			System.out.println(avdr2.getId());
		}

		AQTY aqty = new AQTY();
		System.out.println("\n" + "庫存部門以上權限查詢:指定廠商");
		aqty = query.getAqtys(dao, aqty.getKeys()[3], "169365").get(0);
		System.out.println(aqty.getFgno());
		System.out.println("\n" + "庫存部門以上權限查詢:全部廠商");
		ArrayList<AQTY> aqtys = query.getAqtys(dao, aqty.getKeys()[3], "");
		for (AQTY aqty2 : aqtys) {
			System.out.println(aqty2.getFgno());
		}

		AINVENTORY ainventory = new AINVENTORY();
		System.out.println("\n" + "庫存部門以上權限查詢:指定盤存檔");
		ainventory = query.getAinventorys(dao, ainventory.getKeys()[1], "1234").get(0);
		System.out.println(aqty.getFgno());
		System.out.println("\n" + "庫存部門以上權限查詢:全部盤存檔");
		ArrayList<AINVENTORY> ainventorys = query.getAinventorys(dao, ainventory.getKeys()[1], "");
		for (AINVENTORY ainventory2 : ainventorys) {
			System.out.println(ainventory2.getFgno());
		}
		System.out.println("測試完成");
	}

}
