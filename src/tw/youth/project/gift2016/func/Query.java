package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.avdr.AVDR;
import tw.youth.project.gift2016.sql.user.AEMP;
import tw.youth.project.gift2016.sql.user.AUSER;

public class Query {
	AUSER user;

	public Query(AUSER user) {
		// TODO Auto-generated constructor stub
		// 取得使用者資料
		this.user = user;
	}

	public APRESENT getPresent(DBManager dao, String key, String value) {
		// 取得指定禮品資料檔
		if (!key.equals("") && !value.equals("")) {
			APRESENT apresent = new APRESENT();
			ArrayList<Object[]> arr = dao.query(apresent.getTableName(), key, value, apresent.getLength());
			for (Object[] objects : arr) {
				apresent.setValuesFull(objects);
				return apresent;
			}
		}
		return null;
	}

	public ArrayList<APRESENT> getPresents(DBManager dao) {
		// 取得所有禮品資料檔
		APRESENT apresent = new APRESENT();
		ArrayList<Object[]> arr = dao.query(apresent.getTableName(), apresent.getKeys()[1], "", apresent.getLength());
		ArrayList<APRESENT> apresents = new ArrayList<>();
		for (Object[] objects : arr) {
			apresent.setValuesFull(objects);
			apresents.add(apresent);
			apresent = new APRESENT();
		}
		return apresents;
	}

	// 主管級簽核權限使用之功能
	public ArrayList<AODR> getAodrs(DBManager dao) {//資料不足
		// 取得部門內的所有訂單
		if (user.getAuthority() > 0) {
			AODR aodr = new AODR();
			ArrayList<Object[]> arr = dao.query(aodr.getTableName(), aodr.getKeys()[1], "", aodr.getLength());
			ArrayList<AODR> aodrs = new ArrayList<>();
			for (Object[] objects : arr) {
				aodr.setValuesFull(objects);
				aodrs.add(aodr);
				aodr = new AODR();
			}
			return aodrs;
		}
		return null;
	}

	public ArrayList<AODRDT> getAodrdts(DBManager dao) {//資料不足
		// 取得部門內的所有訂單副檔
		if (user.getAuthority() > 0) {
			AODRDT aodrdt = new AODRDT();
			ArrayList<Object[]> arr = dao.query(aodrdt.getTableName(), aodrdt.getKeys()[1], "", aodrdt.getLength());
			ArrayList<AODRDT> aodrdts = new ArrayList<>();
			for (Object[] objects : arr) {
				aodrdt.setValuesFull(objects);
				aodrdts.add(aodrdt);
				aodrdt = new AODRDT();
			}
			return aodrdts;
		}
		return null;
	}

	// 以下是核銷部門以上層級才能操作的功能
	public AVDR getAvdr(DBManager dao, String key, String value) {
		// 查詢指定廠商的資料
		if (!key.equals("") && !value.equals("") && user.getRole() > 0) {
			AVDR avdr = new AVDR();
			ArrayList<Object[]> arr = dao.query(avdr.getTableName(), avdr.getKeys()[4], "", avdr.getLength());
			for (Object[] objects : arr) {
				avdr.setValuesFull(objects);
				return avdr;
			}
		}
		return null;

	}

	public ArrayList<AVDR> getAvdrs(DBManager dao, String key, String value) {
		// 查詢符合條件之多個廠商的資料
		if (user.getRole() > 0) {
			AVDR avdr = new AVDR();
			ArrayList<Object[]> arr = dao.query(avdr.getTableName(), key, value, avdr.getLength());
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

	public ArrayList<AVDR> getAvdrs(DBManager dao) {
		// 查詢所有廠商的資料
		if (user.getRole() > 0) {
			AVDR avdr = new AVDR();
			ArrayList<Object[]> arr = dao.query(avdr.getTableName(), avdr.getKeys()[4], "", avdr.getLength());
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

	// 以下是管理部門以上層級才能操作的功能，即時查詢介面也不會使用
	public AUSER getUser(DBManager dao, String username) {
		// 查詢指定使用者
		if (!username.equals("") && user.getRole() > 2) {
			AUSER user = new AUSER();
			ArrayList<Object[]> arr = dao.query(user.getTableName(), user.getKeys()[2], username, user.getLength());
			for (Object[] objects : arr) {
				if (objects[2].equals(username)) {
					user.setValuesFull(objects);
					return user;
				}
			}
		}
		return null;
	}

	public ArrayList<AUSER> getUsers(DBManager dao, String key, String value) {
		// 查詢符合條件的使用者們
		if (user.getRole() > 2) {
			AUSER user = new AUSER();
			ArrayList<AUSER> users = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(user.getTableName(), key, value, user.getLength());
			for (Object[] objects : arr) {
				user.setValuesFull(objects);
				users.add(user);
				user = new AUSER();
			}
			return users.size() > 0 ? users : null;
		}
		return null;
	}

	public ArrayList<AUSER> getUsers(DBManager dao) {
		// 查詢所有使用者們
		if (user.getRole() > 2) {
			AUSER user = new AUSER();
			ArrayList<AUSER> users = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(user.getTableName(), user.getKeys()[2], "", user.getLength());
			for (Object[] objects : arr) {
				user.setValuesFull(objects);
				users.add(user);
				user = new AUSER();
			}
			return users.size() > 0 ? users : null;
		}
		return null;
	}

	public AEMP getAemp(DBManager dao, String empno) {
		// 查詢指定員工基本資料檔
		if (empno.equals("") && user.getRole() > 2) {
			AEMP aemp = new AEMP();
			ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], empno, aemp.getLength());
			for (Object[] objects : arr) {
				if (objects[1].equals(empno)) {
					aemp.setValuesFull(objects);
					return aemp;
				}
			}
		}
		return null;
	}

	public ArrayList<AEMP> getAemps(DBManager dao, String key, String value) {
		// 查詢符合條件之多個員工基本資料檔
		if (user.getRole() > 2) {
			AEMP aemp = new AEMP();
			ArrayList<AEMP> aemps = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(aemp.getTableName(), key, value, aemp.getLength());
			for (Object[] objects : arr) {
				aemp.setValuesFull(objects);
				aemps.add(aemp);
				aemp = new AEMP();
			}
			return aemps.size() > 0 ? aemps : null;
		}
		return null;
	}

	public ArrayList<AEMP> getAemps(DBManager dao) {
		// 查詢所有員工基本資料檔
		if (user.getRole() > 2) {
			AEMP aemp = new AEMP();
			ArrayList<AEMP> aemps = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], "", aemp.getLength());
			for (Object[] objects : arr) {
				aemp.setValuesFull(objects);
				aemps.add(aemp);
				aemp = new AEMP();
			}
			return aemps.size() > 0 ? aemps : null;
		}
		return null;
	}
	
	public AIO getAio(DBManager dao, String empno) {
		// 查詢指定員工基本資料檔
		if (empno.equals("") && user.getRole() > 2) {
			AIO aio = new AIO();
			ArrayList<Object[]> arr = dao.query(aio.getTableName(), aio.getKeys()[1], empno, aio.getLength());
			for (Object[] objects : arr) {
				if (objects[1].equals(empno)) {
					aio.setValuesFull(objects);
					return aio;
				}
			}
		}
		return null;
	}

	public ArrayList<AIO> getAios(DBManager dao, String key, String value) {
		// 查詢符合條件之多個員工基本資料檔
		if (user.getRole() > 2) {
			AIO aio = new AIO();
			ArrayList<AIO> aios = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(aio.getTableName(), key, value, aio.getLength());
			for (Object[] objects : arr) {
				aio.setValuesFull(objects);
				aios.add(aio);
				aio = new AIO();
			}
			return aios.size() > 0 ? aios : null;
		}
		return null;
	}

	public ArrayList<AEMP> getAios(DBManager dao) {
		// 查詢所有員工基本資料檔
		if (user.getRole() > 2) {
			AEMP aemp = new AEMP();
			ArrayList<AEMP> aemps = new ArrayList<>();
			ArrayList<Object[]> arr = dao.query(aemp.getTableName(), aemp.getKeys()[1], "", aemp.getLength());
			for (Object[] objects : arr) {
				aemp.setValuesFull(objects);
				aemps.add(aemp);
				aemp = new AEMP();
			}
			return aemps.size() > 0 ? aemps : null;
		}
		return null;
	}

}
