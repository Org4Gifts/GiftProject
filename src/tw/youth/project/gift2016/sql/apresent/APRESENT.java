package tw.youth.project.gift2016.sql.apresent;

public class APRESENT {
	// 禮品基本檔

	private String FGNO = "";
	// 禮品編號
	private String FGNAME = "";
	// 禮品名稱
	private Float PRC = 0.0f;
	// 禮品單價
	private Short GRADE = 0;
	// 禮品等級
	private Short AUTHORITY = 0;
	// 申請層級
	private String STATUS = "";
	// 申請狀態
	private String NOTE1 = "";
	// 備註
	private Integer FQTY = 0;
	// 安全庫存量
	private Integer IQTY = 0; // auto
	// 即時庫存量

	private String[] values = { "FGNO", "FGNAME", "PRC", "GRADE", "AUTHORITY", "STATUS", "NOTE1", "FQTY", "IQTY" };
	private String[] types = { FGNO.getClass().getSimpleName(), FGNAME.getClass().getSimpleName(),
			PRC.getClass().getSimpleName(), GRADE.getClass().getSimpleName(), AUTHORITY.getClass().getSimpleName(),
			STATUS.getClass().getSimpleName(), NOTE1.getClass().getSimpleName(), FQTY.getClass().getSimpleName(),
			IQTY.getClass().getSimpleName() };
	private String primary = "FGNO";
	private String[] unique = { "FGNAME" };
	
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

	public String getFGNO() {
		return FGNO;
	}

	public void setFGNO(String fGNO) {
		FGNO = fGNO;
	}

	public String getFGNAME() {
		return FGNAME;
	}

	public void setFGNAME(String fGNAME) {
		FGNAME = fGNAME;
	}

	public float getPRC() {
		return PRC;
	}

	public void setPRC(float pRC) {
		PRC = pRC;
	}

	public short getGRADE() {
		return GRADE;
	}

	public void setGRADE(short gRADE) {
		GRADE = gRADE;
	}

	public short getAUTHORITY() {
		return AUTHORITY;
	}

	public void setAUTHORITY(short aUTHORITY) {
		AUTHORITY = aUTHORITY;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getNOTE1() {
		return NOTE1;
	}

	public void setNOTE1(String nOTE1) {
		NOTE1 = nOTE1;
	}

	public int getFQTY() {
		return FQTY;
	}

	public void setFQTY(int fQTY) {
		FQTY = fQTY;
	}

	public int getIQTY() {
		return IQTY;
	}

	public void setIQTY(int iQTY) {
		IQTY = iQTY;
	}

}
