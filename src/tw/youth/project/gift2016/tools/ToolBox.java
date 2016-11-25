package tw.youth.project.gift2016.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolBox {
	public static String getCurrentTimeStamp() { // 完整時間戳記
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}

	public static Date toJavaDate(java.sql.Date date) {
		Date jd = new Date();
		jd.setTime(date.getTime());
		return jd;
	}

	public static java.sql.Date toSqlDate(Date date) {
		java.sql.Date sql = new java.sql.Date(date.getTime());
		return sql;
	}

	public static String formatDate(Object date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
