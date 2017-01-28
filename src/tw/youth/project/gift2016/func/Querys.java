package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.adep.ADEP;
import tw.youth.project.gift2016.sql.afab.AFAB;
import tw.youth.project.gift2016.sql.ainventory.AINVENTORY;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.aqty.AQTY;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class Querys {
	AUSER user;

	public Querys(AUSER user) {
		// TODO Auto-generated constructor stub
		// 取得使用者資料
		this.user = user;
	}

	public ArrayList<APRESENT> getApresents(DBManager manager, String key, String value) {
		// 查詢禮品資料檔 value 不填入 即為全查詢
		APRESENT apresent = new APRESENT();
		ArrayList<Object[]> arr = manager.query(apresent.getTableName(), key, value, apresent.getLength());
		ArrayList<APRESENT> apresents = new ArrayList<>();
		for (Object[] objects : arr) {
			apresent.setValuesFull(objects);
			apresents.add(apresent);
			apresent = new APRESENT();
		}
		return apresents;
	}

	public ArrayList<AFAB> getAfabs(DBManager manager, String key, String value) {
		// 查詢廠區 value 不填入 即為全查詢
		AFAB afab = new AFAB();
		ArrayList<Object[]> arr = manager.query(afab.getTableName(), key, value, afab.getLength());
		ArrayList<AFAB> afabs = new ArrayList<>();
		for (Object[] objects : arr) {
			afab.setValuesFull(objects);
			afabs.add(afab);
			afab = new AFAB();
		}
		return afabs;
	}

	public ArrayList<ADEP> getAdeps(DBManager manager, String key, String value) {
		// 查詢部門的訂單 value 不填入 即為全查詢
		ADEP adep = new ADEP();
		ArrayList<Object[]> arr = manager.query(adep.getTableName(), key, value, adep.getLength());
		ArrayList<ADEP> adeps = new ArrayList<>();
		for (Object[] objects : arr) {
			adep.setValuesFull(objects);
			adeps.add(adep);
			adep = new ADEP();
		}
		return adeps;
	}

	public ArrayList<AODR> getAodrs(DBManager manager) {
		// 查詢部門內的訂單
		if (user.getAuthority() > 0) {
			// 課長級簽核權限使用之功能
			AEMP aemp = new AEMP();
			ArrayList<AODR> aodrs = new ArrayList<>();
			for (Object[] objs : manager.query(aemp.getTableName(), aemp.getKeys()[8], user.getDno(),
					aemp.getLength())) {
				AODR aodr = new AODR();
				for (Object[] objects : manager.query(aodr.getTableName(), aodr.getKeys()[4], objs[8],
						aodr.getLength())) {
					aodr.setValuesFull(objects);
					aodrs.add(aodr);
					aodr = new AODR();
				}
			}

			return aodrs;

		} else {
			// 一般職員使用之功能 查詢自己的訂單
			AODR aodr = new AODR();
			ArrayList<AODR> aodrs = new ArrayList<>();
			for (Object[] objects : manager.query(aodr.getTableName(), user.getKeys()[1], user.getEmpno(),
					aodr.getLength())) {
				aodr.setValuesFull(objects);
				aodrs.add(aodr);
				aodr = new AODR();
			}
			return aodrs;
		}
	}

	public ArrayList<AODRDT> getAodrdts(DBManager manager, String value) {
		// 查詢訂單的訂單副檔 value不填入 即為全查詢，但此方法因需搭配aodr，所以這裡是指定查詢
		AODRDT aodrdt = new AODRDT();
		ArrayList<Object[]> arr = manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], value, aodrdt.getLength());
		ArrayList<AODRDT> aodrdts = new ArrayList<>();
		for (Object[] objects : arr) {
			aodrdt.setValuesFull(objects);
			aodrdts.add(aodrdt);
			aodrdt = new AODRDT();
		}
		return aodrdts;
	}

	public ArrayList<ASIGNLOG> getAsignlogs(DBManager manager, String value) {
		// 查詢簽核人員 value不填入 即為全查詢，但此方法因需搭配aodr或aio，所以這裡是指定查詢
		// AODR(一般訂單)跟AIO(調撥單)共用功能
		ASIGNLOG asignlog = new ASIGNLOG();
		ArrayList<Object[]> arr = manager.query(asignlog.getTableName(), asignlog.getKeys()[1], value,
				asignlog.getLength());
		ArrayList<ASIGNLOG> asignlogs = new ArrayList<>();
		for (Object[] objects : arr) {
			asignlog.setValuesFull(objects);
			asignlogs.add(asignlog);
			asignlog = new ASIGNLOG();
		}
		return asignlogs;
	}

	// 以下是核銷部門以上層級才能操作的功能
	public ArrayList<AVDR> getAvdrs(DBManager manager, String key, String value) {
		// 查詢符合條件之多個廠商的資料 value 不填入 即為全查詢
		if (user.getRole() > 0) {
			AVDR avdr = new AVDR();
			ArrayList<Object[]> arr = manager.query(avdr.getTableName(), key, value, avdr.getLength());
			ArrayList<AVDR> avdrs = new ArrayList<>();
			for (Object[] objects : arr) {
				avdr.setValuesFull(objects);
				avdrs.add(avdr);
				avdr = new AVDR();
			}
			return avdrs.size() > 0 ? avdrs : null;
		}
		return null;
	}

	// 以下是庫存部門以上層級才能操作的功能

	public ArrayList<AQTY> getAqtys(DBManager manager, String key, String value) {
		// 查詢符合條件之多廠別進銷匯總檔 value 不填入 即為全查詢
		if (user.getRole() > 1) {
			AQTY aqty = new AQTY();
			ArrayList<Object[]> arr = manager.query(aqty.getTableName(), key, value, aqty.getLength());
			ArrayList<AQTY> aqtys = new ArrayList<>();
			for (Object[] objects : arr) {
				aqty.setValuesFull(objects);
				aqtys.add(aqty);
				aqty = new AQTY();
			}
			return aqtys.size() > 0 ? aqtys : null;
		}
		return null;
	}

	public ArrayList<AINVENTORY> getAinventorys(DBManager manager, String key, String value) {
		// 查詢符合條件之多個盤存檔 value 不填入 即為全查詢
		if (user.getRole() > 1) {
			AINVENTORY ainventory = new AINVENTORY();
			ArrayList<Object[]> arr = manager.query(ainventory.getTableName(), key, value, ainventory.getLength());
			ArrayList<AINVENTORY> ainventorys = new ArrayList<>();
			for (Object[] objects : arr) {
				ainventory.setValuesFull(objects);
				ainventorys.add(ainventory);
				ainventory = new AINVENTORY();
			}
			return ainventorys.size() > 0 ? ainventorys : null;
		}
		return null;
	}

	// 以下是管理部門以上層級才能操作的功能，即時查詢介面也不會使用

	public ArrayList<AUSER> getUsers(DBManager manager, String key, String value) {
		// 查詢符合條件的使用者們 value 不填入 即為全查詢
		if (user.getRole() > 1) {
			AUSER user = new AUSER();
			ArrayList<AUSER> users = new ArrayList<>();
			ArrayList<Object[]> arr = manager.query(user.getTableName(), key, value, user.getLength());
			for (Object[] objects : arr) {
				user.setValuesFull(objects);
				users.add(user);
				user = new AUSER();
			}
			return users.size() > 0 ? users : null;
		}
		return null;
	}

	public ArrayList<AEMP> getAemps(DBManager manager, String key, String value) {
		// 查詢符合條件之多個員工基本資料檔 value 不填入 即為全查詢
		if (user.getRole() > 1) {
			AEMP aemp = new AEMP();
			ArrayList<AEMP> aemps = new ArrayList<>();
			ArrayList<Object[]> arr = manager.query(aemp.getTableName(), key, value, aemp.getLength());
			for (Object[] objects : arr) {
				aemp.setValuesFull(objects);
				aemps.add(aemp);
				aemp = new AEMP();
			}
			return aemps.size() > 0 ? aemps : null;
		}
		return null;
	}

	public ArrayList<AIO> getAios(DBManager manager, String key, String value) {
		// 查詢符合條件之多個調撥單狀態 value 不填入 即為全查詢 目前兩個權限的功能一樣
		if (user.getRole() > 1 && user.getAuthority() > 0) {
			// 管理部門以上單位以及課長級以上職位才可使用
			AIO aio = new AIO();
			ArrayList<AIO> aios = new ArrayList<>();
			ArrayList<Object[]> arr = manager.query(aio.getTableName(), key, value, aio.getLength());
			for (Object[] objects : arr) {
				aio.setValuesFull(objects);
				aios.add(aio);
				aio = new AIO();
			}
			return aios.size() > 0 ? aios : null;
		} else if (user.getRole() > 1) {
			// 管理部門以上單位才可使用
			AIO aio = new AIO();
			ArrayList<AIO> aios = new ArrayList<>();
			ArrayList<Object[]> arr = manager.query(aio.getTableName(), key, value, aio.getLength());
			for (Object[] objects : arr) {
				aio.setValuesFull(objects);
				aios.add(aio);
				aio = new AIO();
			}
			return aios.size() > 0 ? aios : null;
		}
		return null;
	}

	// public ArrayList<AIO> getAios(DBManager dao) {
	// // 查詢所有調撥單狀態
	// if (user.getRole() > 2 && user.getAuthority() > 0) {
	// AIO aio = new AIO();
	// ArrayList<AIO> aios = new ArrayList<>();
	// ArrayList<Object[]> arr = dao.query(aio.getTableName(), aio.getKeys()[1],
	// "", aio.getLength());
	// for (Object[] objects : arr) {
	// aio.setValuesFull(objects);
	// aios.add(aio);
	// aio = new AIO();
	// }
	// return aios.size() > 0 ? aios : null;
	// }
	// return null;
	// }

	public ArrayList<AIODT> getAiodts(DBManager manager, String value) {
		// 查詢調撥單的副檔
		AIODT aiodt = new AIODT();
		ArrayList<AIODT> aiodts = new ArrayList<>();
		ArrayList<Object[]> arr = manager.query(aiodt.getTableName(), aiodt.getKeys()[1], value, aiodt.getLength());
		for (Object[] objects : arr) {
			aiodt.setValuesFull(objects);
			aiodts.add(aiodt);
			aiodt = new AIODT();
		}
		return aiodts;
	}

}
