package tw.youth.project.gift2016.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolBox {
	
	public static String getStrCurrentTimestamp() { // 完整時間戳記
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

	public static java.sql.Date timestampToSqlDate(Timestamp timestamp) {
		return new java.sql.Date(timestamp.getTime());
	}

	public static String formatDate(Object date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp strToSqlTimestamp(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return new Timestamp(sdf.parse(str).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return null;
		}
	}
}
