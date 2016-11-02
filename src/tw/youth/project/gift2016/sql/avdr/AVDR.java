package tw.youth.project.gift2016.sql.avdr;

public class AVDR {
	// 廠/客基本資料檔

	private String CONO = "";
	// 公司編號
	private String NA = "";
	// 公司簡稱
	private String NAME = "";
	// 公司名稱
	private String ID = "";
	// 統一編號
	private String BOSE = "";
	// 負責人
	private String AGENT = "";
	// 業務代表
	private String TITLE = "";
	// 職稱
	private String TEL1 = "";
	// 電話1
	private String TEL2 = "";
	// 電話2
	private String PTEL = "";
	// 手機
	private String FAX = "";
	// 傳真
	private String IADD = "";
	// 公司地址
	private String MEMO = "";
	// 備註

	private String[] values = { "CONO", "NA", "NAME", "ID", "BOSE", "AGENT", "TITLE", "TEL1", "TEL2", "PTEL", "FAX",
			"IADD", "MEMO" };
	private String[] types = { CONO.getClass().getSimpleName(), NA.getClass().getSimpleName(),
			NAME.getClass().getSimpleName(), ID.getClass().getSimpleName(), BOSE.getClass().getSimpleName(),
			AGENT.getClass().getSimpleName(), TITLE.getClass().getSimpleName(), TEL1.getClass().getSimpleName(),
			TEL2.getClass().getSimpleName(), PTEL.getClass().getSimpleName(), FAX.getClass().getSimpleName(),
			IADD.getClass().getSimpleName(), MEMO.getClass().getSimpleName() };
	
	private String primary = "CONO";
	private String[] unique = { "ID" };

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

	public String getCONO() {
		return CONO;
	}

	public void setCONO(String cONO) {
		CONO = cONO;
	}

	public String getNA() {
		return NA;
	}

	public void setNA(String nA) {
		NA = nA;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getBOSE() {
		return BOSE;
	}

	public void setBOSE(String bOSE) {
		BOSE = bOSE;
	}

	public String getAGENT() {
		return AGENT;
	}

	public void setAGENT(String aGENT) {
		AGENT = aGENT;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getTEL1() {
		return TEL1;
	}

	public void setTEL1(String tEL1) {
		TEL1 = tEL1;
	}

	public String getTEL2() {
		return TEL2;
	}

	public void setTEL2(String tEL2) {
		TEL2 = tEL2;
	}

	public String getPTEL() {
		return PTEL;
	}

	public void setPTEL(String pTEL) {
		PTEL = pTEL;
	}

	public String getFAX() {
		return FAX;
	}

	public void setFAX(String fAX) {
		FAX = fAX;
	}

	public String getIADD() {
		return IADD;
	}

	public void setIADD(String iADD) {
		IADD = iADD;
	}

	public String getMEMO() {
		return MEMO;
	}

	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}

}
