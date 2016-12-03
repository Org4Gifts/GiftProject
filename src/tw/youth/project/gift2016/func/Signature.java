package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AUSER;

public class Signature {

	// 取得單筆訂單/調撥單
	public Object getOrder(DBManager dao, AUSER user, String key, String orderNum) {
		Querys query = new Querys(user);
		if (key.equals("aodr")) {
			ArrayList<AODR> arr = query.getAodrs(dao);
			for (AODR aodr : arr) {
				if (aodr.getOrder1().equals(orderNum))
					return aodr;
			}
		}

		if (key.equals("aio")) {
			ArrayList<AIO> arr = query.getAios(dao, key, orderNum);
			return arr.get(0);
		}

		return null;
	}

	// 取得多筆訂單/調撥單
	public Object getOrders(DBManager dao, AUSER user, String key, String orderNum) {
		Querys query = new Querys(user);
		if (key.equals("aodr"))
			return query.getAodrs(dao);

		if (key.equals("aio"))
			return query.getAios(dao, key, orderNum);

		return null;
	}

	public <T> String approveOrder(DBManager dao, AUSER user, T obj) {
		if (obj instanceof AODR) {
			AODR aodr = (AODR) obj;
			aodr.setSignerno(user.getMgr());
			ASIGNLOG signlog = new ASIGNLOG();
			
			return dao.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull());
		} else {
			AIO aio = (AIO) obj;
			aio.setSignerno(user.getMgr());
			return dao.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull());
		}
	}

}
