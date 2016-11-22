package tw.youth.project.gift2016.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolBox {
	public static String getCurrentTimeStamp() { // 完整時間戳記
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String strDate = sdf.format(now);
		return strDate;
	}
}
