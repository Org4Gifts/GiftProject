package tw.youth.project.gift2016.sql.aqty;

public class AQTY {
	// 多廠別進/銷彙總檔

	private Integer YYMM = 0; // auto
	// 資料年月
	private String FNO = ""; // auto
	// 廠別
	private String FGNO = ""; // auto
	// 禮品編號
	private Integer PMQTY = 0; // auto
	// 期初數量
	private Integer INQTY = 0; // auto
	// 本月進貨
	private Integer UDQTY = 0; // auto
	// 本月銷貨

	private String[] values = { "YYMM", "FNO", "FGNO", "PMQTY", "INQTY", "UDQTY" };
	private String[] types = { YYMM.getClass().getSimpleName(), FNO.getClass().getSimpleName(),
			FGNO.getClass().getSimpleName(), PMQTY.getClass().getSimpleName(), INQTY.getClass().getSimpleName(),
			UDQTY.getClass().getSimpleName() };
	private String primary = "";
	private String[] unique = { "" };

	public String getTableName() {
		return getClass().getSimpleName();
	}

	public String[] getValues() {
		return values;
	}

	public String[] getTypes() {
		return types;
	}

	public String getPrimary() {
		return primary;
	}

	public String[] getUnique() {
		return unique;
	}
	// 以下是儲存的值

	public int getYYMM() {
		return YYMM;
	}

	public void setYYMM(int yYMM) {
		YYMM = yYMM;
	}

	public String getFNO() {
		return FNO;
	}

	public void setFNO(String fNO) {
		FNO = fNO;
	}

	public String getFGNO() {
		return FGNO;
	}

	public void setFGNO(String fGNO) {
		FGNO = fGNO;
	}

	public int getPMQTY() {
		return PMQTY;
	}

	public void setPMQTY(int pMQTY) {
		PMQTY = pMQTY;
	}

	public int getINQTY() {
		return INQTY;
	}

	public void setINQTY(int iNQTY) {
		INQTY = iNQTY;
	}

	public int getUDQTY() {
		return UDQTY;
	}

	public void setUDQTY(int uDQTY) {
		UDQTY = uDQTY;
	}

}
