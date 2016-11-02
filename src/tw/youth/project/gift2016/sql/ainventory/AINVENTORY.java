package tw.youth.project.gift2016.sql.ainventory;

public class AINVENTORY {
	// 多廠別盤存檔

	private String INVO = "";
	// 盤點單號
	private String FNO = "";
	// 儲存廠別
	private Integer YYMM = 0;
	// 資料年月
	private String FGNO = "";
	// 禮品編號
	private Integer IVQTY = 0;
	// 盤點數量
	private Integer SQTY = 0; // auto
	// 系統數量

	private String[] values = { "INVO", "FNO", "YYMM", "FGNO", "IVQTY", "SQTY" };
	private String[] types = { INVO.getClass().getSimpleName(), FNO.getClass().getSimpleName(),
			YYMM.getClass().getSimpleName(), FGNO.getClass().getSimpleName(), IVQTY.getClass().getSimpleName(),
			SQTY.getClass().getSimpleName() };
	private String primary = "INVO";
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
	//以下是儲存的值

	public String getINVO() {
		return INVO;
	}

	public void setINVO(String iNVO) {
		INVO = iNVO;
	}

	public String getFNO() {
		return FNO;
	}

	public void setFNO(String fNO) {
		FNO = fNO;
	}

	public int getYYMM() {
		return YYMM;
	}

	public void setYYMM(int yYMM) {
		YYMM = yYMM;
	}

	public String getFGNO() {
		return FGNO;
	}

	public void setFGNO(String fGNO) {
		FGNO = fGNO;
	}

	public int getIVQTY() {
		return IVQTY;
	}

	public void setIVQTY(int iVQTY) {
		IVQTY = iVQTY;
	}

	public int getSQTY() {
		return SQTY;
	}

	public void setSQTY(int sQTY) {
		SQTY = sQTY;
	}

}
