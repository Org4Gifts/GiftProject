package tw.youth.project.gift2016.sql.adep;

public class ADEP {
	// 部門基本資料檔
	
	private String DNO = "";
	// 部門代碼
	private String DNAME = "";
	// 部門名稱
	private String FNO = "";
	// 所在廠區

	private String[] values = { "DNO", "DNAME", FNO };
	private String[] types = { DNO.getClass().getSimpleName(), DNAME.getClass().getSimpleName(),
			FNO.getClass().getSimpleName() };
	private String primary = "DNO";
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

	public String getDNO() {
		return DNO;
	}

	public void setDNO(String dNO) {
		DNO = dNO;
	}

	public String getDNAME() {
		return DNAME;
	}

	public void setDNAME(String dNAME) {
		DNAME = dNAME;
	}

	public String getFNO() {
		return FNO;
	}

	public void setFNO(String fNO) {
		FNO = fNO;
	}

}
