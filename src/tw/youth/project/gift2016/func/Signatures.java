package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aio.AIODT;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.aodr.AODRDT;
import tw.youth.project.gift2016.sql.apresent.APRESENT;
import tw.youth.project.gift2016.sql.asetup.ASETUP;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Signatures {

	private StringBuilder sb = new StringBuilder();

	// 取得單筆訂單/調撥單
	public Object getOrder(DBManager manager, AUSER user, String key, String orderNum) {
		// 特殊查詢 查詢使用者
		if (user != null && !user.getEname().equals("")) {
			if (key.equals("aodr")) {
				AODR aodr = new AODR();
				for (Object[] objs : manager.query(aodr.getTableName(), aodr.getKeys()[1], orderNum,
						aodr.getLength())) {
					aodr.setValuesFull(objs);
					if (aodr.getOrder1().equals(orderNum))
						return aodr;
				}
			}

			if (key.equals("aio")) {
				AIO aio = new AIO();
				for (Object[] objs : manager.query(aio.getTableName(), aio.getKeys()[1], orderNum, aio.getLength())) {
					aio.setValuesFull(objs);
					if (aio.getVhno().equals(orderNum))
						return aio;
				}

			}
		}
		return null;
	}

	// 取得多筆訂單/調撥單
	public ArrayList<Object> getOrders(DBManager manager, AUSER user, String key) {
		if (user != null && !user.getEname().equals("")) {
			// Querys query = new Querys(user);
			ArrayList<Object> arr = new ArrayList<>();
			if (key.equals("aodr")) {
				AODR aodr = new AODR();
				for (Object[] arr2 : manager.query(aodr.getTableName(), aodr.getKeys()[11], user.getEmpno(),
						aodr.getLength())) {
					aodr.setValuesFull(arr2);
					arr.add(aodr);
					aodr = new AODR();
				}
				return arr;
			}

			if (key.equals("aio")) {
				AIO aio = new AIO();
				for (Object[] arr2 : manager.query(aio.getTableName(), aio.getKeys()[14], user.getEmpno(),
						aio.getLength())) {
					aio.setValuesFull(arr2);
					arr.add(aio);
					aio = new AIO();
				}
				return arr;
			}
		}
		return null;
	}

	// 查詢廠區管理部門簽核者
	public String getLastSigner(DBManager manager, AUSER user) {
		ASETUP asetup = new ASETUP();
		for (Object[] objs : manager.query(asetup.getTableName(), asetup.getKeys()[1], user.getFno(),
				asetup.getLength())) {
			asetup.setValuesFull(objs);
		}
		return asetup.getEmpno();
	}

	// 同意訂/調撥單
	public <T> String approveOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals(""))
			return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_APPROVE);

		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 拒絕訂/調撥單
	public <T> String rejectOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals(""))
			return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_REJECT);

		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 完成訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String completeOrder(DBManager manager, AUSER user, T obj) {
		System.out.println(user.getUser()+" "+user.getRole());
		if (user != null && !user.getEname().equals("")) {
			if (user.getRole() == 3) {
				refreshApresent(manager, user, obj, true);
				return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_COMPLETE);
			} else
				return ConstValue.PERMISSION_NOT_ENOUGH;
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 作廢訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String obsoleteOrder(DBManager manager, AUSER user, T obj) {
		if (user != null && !user.getEname().equals("")) {
			if (user.getRole() == 3) {
				if (sb.length() > 0)
					sb.delete(0, sb.length());
				if (obj instanceof AODR) {
					AODR aodr = (AODR) obj;
					aodr.setSignerno(user.getEmpno());
					aodr.setStatus(ConstValue.ORDERS_STATUS_OBSOLETED);
					refreshApresent(manager, user, obj, false);
					return manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull());
				} else {
					AIO aio = (AIO) obj;
					aio.setSignerno(user.getEmpno());
					aio.setStatus(ConstValue.ORDERS_STATUS_OBSOLETED);
					return manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull());
				}
			} else
				return ConstValue.PERMISSION_NOT_ENOUGH;
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

	// 庫存功能
	public <T> void refreshApresent(DBManager manager, AUSER user, T obj, boolean completeOrObsolete) {
		if (obj instanceof AODR) {
			AODR aodr = (AODR) obj;
			AODRDT aodrdt = new AODRDT();
			for (Object[] objs : manager.query(aodrdt.getTableName(), aodrdt.getKeys()[1], aodr.getOrder1(),
					aodrdt.getLength())) {
				aodrdt.setValuesFull(objs);
				APRESENT apresent = new APRESENT();
				apresent.setValuesFull(manager
						.query(apresent.getTableName(), apresent.getKeys()[1], aodrdt.getFgno(), apresent.getLength())
						.get(0));
				apresent.setIqty(completeOrObsolete ? apresent.getIqty() - aodrdt.getQty() : apresent.getIqty() + aodrdt.getQty());
				manager.update(apresent.getTableName(), apresent.getKeys(), apresent.getValuesFull());
				aodrdt = new AODRDT();
			}
		}

		if (obj instanceof AIO) {
			AIO aio = (AIO) obj;
			AIODT aiodt = new AIODT();
			for (Object[] objs : manager.query(aiodt.getTableName(), aiodt.getKeys()[1], aio.getVhno(),
					aiodt.getLength())) {
				aiodt.setValuesFull(objs);
				APRESENT apresent = new APRESENT();
				apresent.setValuesFull(manager
						.query(apresent.getTableName(), apresent.getKeys()[1], aiodt.getInno(), apresent.getLength())
						.get(0));
				if (aio.getAno().equals(user.getFno()))
					apresent.setIqty(completeOrObsolete ? apresent.getIqty() + aiodt.getQty() : apresent.getIqty() - aiodt.getQty());
				else
					apresent.setIqty(completeOrObsolete ? apresent.getIqty() - aiodt.getQty() : apresent.getIqty() + aiodt.getQty());

				manager.update(apresent.getTableName(), apresent.getKeys(), apresent.getValuesFull());
				aiodt = new AIODT();
			}
		}

	}

	// 訂/調撥單功能
	public <T> String orderFunc(DBManager manager, AUSER user, T obj, String cmd) {
		if (user != null && !user.getEname().equals("")) {
			if (sb.length() > 0)
				sb.delete(0, sb.length());
			ASIGNLOG asignlog = new ASIGNLOG();
			Object[] objs;
			String signerno = getLastSigner(manager, user);
			if (obj instanceof AODR) {
				AODR aodr = (AODR) obj;
				if (!user.getMgr().equals("P0001")) {
					if (!cmd.equals(ConstValue.ORDERS_STATUS_OBSOLETED))
						aodr.setSignerno(user.getMgr());
				} else {
					if (!user.getEmpno().equals(signerno))
						aodr.setSignerno(signerno);
				}
				// 特殊查詢功能
				ArrayList<Object[]> arr = manager.query(asignlog.getTableName(), asignlog.getKeys()[1], aodr.getOrder1(),
						asignlog.getLength());
				asignlog.setValuesFull(arr.get(arr.size() - 1));

				objs = new Object[] { aodr.getOrder1(), user.getEmpno(), user.getEname(), user.getDno(),
						((float) (ToolBox.getCurrentTimestamp().getTime() - asignlog.getCreated().getTime()))
								/ (60 * 60 * 1000),
						cmd };
				objs[objs.length - 2] = (float) objs[objs.length - 2] < 0.0f ? 0.0f : objs[objs.length - 2];
				asignlog.setValues(objs);
				sb.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append("\n");
				sb.append(manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues())).append("\n");
				return sb.toString();
			} else {
				AIO aio = (AIO) obj;
				if (!user.getMgr().equals("P0001")) {
					if (!cmd.equals(ConstValue.ORDERS_STATUS_OBSOLETED))
						aio.setSignerno(user.getMgr());
				} else {
					if (!user.getEmpno().equals(signerno))
						aio.setSignerno(getLastSigner(manager, user));
				}

				// 特殊查詢功能
				ArrayList<Object[]> arr = manager.query(asignlog.getTableName(), asignlog.getKeys()[1], aio.getVhno(),
						asignlog.getLength());
				asignlog.setValuesFull(arr.get(arr.size() - 1));
				objs = new Object[] { aio.getVhno(), user.getEmpno(), user.getEname(), user.getDno(),
						((float) (ToolBox.getCurrentTimestamp().getTime() - asignlog.getCreated().getTime()))
								/ (60 * 60 * 1000),
						cmd };
				objs[objs.length - 2] = (float) objs[objs.length - 2] < 0.0f ? 0.0f : objs[objs.length - 2];
				asignlog.setValues(objs);
				sb.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append("\n");
				sb.append(manager.insert(asignlog.getTableName(), asignlog.getKeys(), asignlog.getValues())).append("\n");
				return sb.toString();
			}
		}
		return ConstValue.LOGIN_NOT_LOGIN;
	}

}
