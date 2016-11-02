package tw.youth.project.gift2016.sql.aodr;

public class AODR {
	// 訂單主檔

	private String ORDER1 = "";
	// 訂單編號
	private Integer ODATE = 0;
	// 訂定日期
	private String EMPNO = "";
	// 員工編號
	private Long TAMT = 0L;
	// 訂單金額
	private String PURPOSE = "";
	// 需求目的

	private String[] values = { "ORDER1", "ODATE", "EMPNO", "TAMT", "PURPOSE" };
	private String[] types = { ORDER1.getClass().getSimpleName(), ODATE.getClass().getSimpleName(),
			EMPNO.getClass().getSimpleName(), TAMT.getClass().getSimpleName(), PURPOSE.getClass().getSimpleName() };
	private String primary = "ORDER1";
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

	public String getORDER1() {
		return ORDER1;
	}

	public void setORDER1(String oRDER1) {
		ORDER1 = oRDER1;
	}

	public int getODATE() {
		return ODATE;
	}

	public void setODATE(int oDATE) {
		ODATE = oDATE;
	}

	public String getEMPNO() {
		return EMPNO;
	}

	public void setEMPNO(String eMPNO) {
		EMPNO = eMPNO;
	}

	public long getTAMT() {
		return TAMT;
	}

	public void setTAMT(long tAMT) {
		TAMT = tAMT;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}

}
