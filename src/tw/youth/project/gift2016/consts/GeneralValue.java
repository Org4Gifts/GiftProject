package tw.youth.project.gift2016.consts;

import java.util.HashMap;
import java.util.Map;

public class GeneralValue {
	
	private static Map<String,String> queryKeys;
	static {
		//所有代碼對應的中文字都在這裡，要改稱呼請來此更改
		queryKeys = new HashMap<>();
		//原本設定一般查詢使用的關鍵字
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
		//以下屬新增事項，主要用意為方便建立搜尋標題使用
		queryKeys.put("user", "使用者名稱");
		queryKeys.put("pass", "使用者密碼");
		queryKeys.put("created", "建立日期");
		queryKeys.put("updated", "修改日期");
		queryKeys.put("mgr", "直屬主管工號");
		queryKeys.put("role", "部門權限");
		queryKeys.put("ext", "分機");
		queryKeys.put("email", "電子郵件");
		queryKeys.put("status", "狀態");
		queryKeys.put("prc", "單價");
		queryKeys.put("qty", "數量");
		queryKeys.put("tamt", "總金額");
		queryKeys.put("memo", "備註");
		queryKeys.put("note1", "備註");
		//以下屬廠客關鍵字
		queryKeys.put("cono", "公司編號");
		queryKeys.put("na", "公司簡稱");
		queryKeys.put("name", "公司名稱");
		queryKeys.put("bose", "負責人");
		queryKeys.put("agent", "業務代表");
		queryKeys.put("title", "職稱");
		queryKeys.put("tel1", "電話1");
		queryKeys.put("tel2", "電話2");
		queryKeys.put("ptel", "手機");
		queryKeys.put("fax", "傳真");
		queryKeys.put("iadd", "公司地址");
		//簽核關鍵字
		queryKeys.put("signature", "簽核人員名稱");
		queryKeys.put("spent", "簽核花費時間");
		//進銷關鍵字
		queryKeys.put("pmqty", "期初數量");
		queryKeys.put("inqty", "本月進貨");
		queryKeys.put("udqty", "本月銷貨");
		//禮品關鍵字
		queryKeys.put("fqty", "安全庫存量");
		queryKeys.put("iqty", "即時庫存量");
		//訂單關鍵字
		queryKeys.put("comname", "拜訪公司名稱");
		queryKeys.put("pername", "拜訪對象");
		queryKeys.put("odate", "訂定日期");
		queryKeys.put("signerno", "目前簽核人員");//訂/調共用
		queryKeys.put("purpose", "需求目的");
		queryKeys.put("signerlist", "簽核人員名單");//訂/調共用
		//調撥關鍵字
		queryKeys.put("inno", "轉出禮品編號");
		queryKeys.put("outno", "要轉出禮品編號");
		queryKeys.put("ano", "轉入廠別");
		//盤點關鍵字
		queryKeys.put("ivqty", "盤點數量");
		queryKeys.put("sqty", "系統數量");
		
		
	}
	
	public static String getQueryKey(String key){
		return queryKeys.get(key);
	}
}
