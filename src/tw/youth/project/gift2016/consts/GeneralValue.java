package tw.youth.project.gift2016.consts;

import java.util.HashMap;
import java.util.Map;

public class GeneralValue {
	
	private static Map<String,String> queryKeys;
	static {
		queryKeys = new HashMap<>();
		queryKeys.put("empno", "員工工號");
		queryKeys.put("ename", "中文姓名");
		queryKeys.put("job", "職稱");
		queryKeys.put("dno", "部門代碼");
		queryKeys.put("authority", "權限");
		queryKeys.put("order1", "訂單編號");
		queryKeys.put("yymm", "年月(格式:YYMM)");
		queryKeys.put("fgno", "禮品編號");
		queryKeys.put("grade", "禮品等級");
		queryKeys.put("fgname", "禮品名稱");
		queryKeys.put("vhno", "單據編號");
		queryKeys.put("vhdt", "單據日期(YYYY-MM-dd)");
		queryKeys.put("vrdt", "需求日期(YYYY-MM-dd)");
		queryKeys.put("invo", "盤點單號");
		queryKeys.put("fname", "廠別名稱");
		queryKeys.put("fno", "廠別代碼");
		queryKeys.put("dname", "部門名稱");
		
	}
	
	public static String getQueryKey(String key){
		return queryKeys.get(key);
	}
}
