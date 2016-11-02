package tw.youth.project.gift2016.sql.afab;

public class AFAB {
	// 廠別基本檔

	private String FNO = "";
	// 廠別代碼
	private String FNAME = "";
	// 廠別名稱

	private String[] values = { "FNO", "FNAME" };
	private String[] types = { FNO.getClass().getSimpleName(), FNAME.getClass().getSimpleName() };
	private String primary = "FNO";
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

	public String getFNO() {
		return FNO;
	}

	public void setFNO(String fNO) {
		FNO = fNO;
	}

	public String getFNAME() {
		return FNAME;
	}

	public void setFNAME(String fNAME) {
		FNAME = fNAME;
	}
}
