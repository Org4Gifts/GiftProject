package tw.youth.project.gift2016.func;

import java.util.ArrayList;

import tw.youth.project.gift2016.consts.ConstValue;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.aio.AIO;
import tw.youth.project.gift2016.sql.aodr.AODR;
import tw.youth.project.gift2016.sql.asignlog.ASIGNLOG;
import tw.youth.project.gift2016.sql.user.AUSER;
import tw.youth.project.gift2016.tools.ToolBox;

public class Signatures {

	private StringBuilder sb = new StringBuilder();

	// 取得單筆訂單/調撥單
	public Object getOrder(DBManager manager, AUSER user, String key, String orderNum) {
		Querys query = new Querys(user);
		if (key.equals("aodr")) {
			ArrayList<AODR> arr = query.getAodrs(manager);
			for (AODR aodr : arr) {
				if (aodr.getOrder1().equals(orderNum))
					return aodr;
			}
		}

		if (key.equals("aio")) {
			ArrayList<AIO> arr = query.getAios(manager, key, orderNum);
			return arr.get(0);
		}

		return null;
	}

	// 取得多筆訂單/調撥單
	public Object getOrders(DBManager manager, AUSER user, String key, String orderNum) {
		Querys query = new Querys(user);
		if (key.equals("aodr"))
			return query.getAodrs(manager);

		if (key.equals("aio"))
			return query.getAios(manager, key, orderNum);

		return null;
	}

	// 同意訂/調撥單
	public <T> String approveOrder(DBManager manager, AUSER user, T obj) {
		return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_APPROVE);
	}

	// 拒絕訂/調撥單
	public <T> String rejectOrder(DBManager manager, AUSER user, T obj) {
		return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_REJECT);
	}

	// 完成訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String completeOrder(DBManager manager, AUSER user, T obj) {
		if (user.getRole() == 3)
			return orderFunc(manager, user, obj, ConstValue.SIGNATURE_STATUS_COMPLETE);
		else
			return ConstValue.PERMISSION_NOT_ENOUGH;
	}

	// 作廢訂/調撥單 role=3的管理部門使用，還有更新庫存沒做
	public <T> String obsoleteOrder(DBManager manager, AUSER user, T obj) {
		if (user.getRole() == 3) {
			if (sb.length() > 0)
				sb.delete(0, sb.length());
			if (obj instanceof AODR) {
				AODR aodr = (AODR) obj;
				aodr.setSignerno(user.getEmpno());
				aodr.setStatus(ConstValue.ORDERS_STATUS_OBSOLETED);
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

	// 訂/調撥單功能
	public <T> String orderFunc(DBManager manager, AUSER user, T obj, String cmd) {
		if (sb.length() > 0)
			sb.delete(0, sb.length());
		ASIGNLOG signlog = new ASIGNLOG();
		Object[] objs;
		if (obj instanceof AODR) {
			AODR aodr = (AODR) obj;
			aodr.setSignerno(user.getMgr());
			ArrayList<Object[]> arr = manager.query(signlog.getTableName(), signlog.getKeys()[1], aodr.getOrder1(),
					signlog.getLength());
			signlog.setValuesFull(arr.get(arr.size() - 1));

			objs = new Object[] { aodr.getOrder1(), user.getEmpno(), user.getEname(), user.getDno(),
					((float) (ToolBox.getCurrentTimestamp().getTime() - signlog.getCreated().getTime()))
							/ (60 * 60 * 1000),
					cmd };
			System.out.println(objs[4]);
			signlog.setValues(objs);
			sb.append(manager.update(aodr.getTableName(), aodr.getKeys(), aodr.getValuesFull())).append("\n");
			sb.append(manager.insert(signlog.getTableName(), signlog.getKeys(), signlog.getValues())).append("\n");
			return sb.toString();
		} else {
			AIO aio = (AIO) obj;
			aio.setSignerno(user.getMgr());
			ArrayList<Object[]> arr = manager.query(signlog.getTableName(), signlog.getKeys()[1], aio.getVhno(),
					signlog.getLength());
			signlog.setValuesFull(arr.get(arr.size() - 1));
			objs = new Object[] { aio.getVhno(), user.getEmpno(), user.getEname(), user.getDno(),
					((float) (ToolBox.getCurrentTimestamp().getTime() - signlog.getCreated().getTime()))
							/ (60 * 60 * 1000),
					cmd };
			signlog.setValues(objs);
			sb.append(manager.update(aio.getTableName(), aio.getKeys(), aio.getValuesFull())).append("\n");
			sb.append(manager.insert(signlog.getTableName(), signlog.getKeys(), signlog.getValues())).append("\n");
			return sb.toString();
		}
	}

}
