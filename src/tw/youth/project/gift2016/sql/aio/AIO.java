package tw.youth.project.gift2016.sql.aio;

public class AIO {
	// 多廠別進/銷主檔

	private String VHNO = "";
	// 單據編號
	private String FNO = "";
	// 廠別
	private Integer VHDT = 0;
	// 單據日期
	private String ANO = "";
	// 單位編號
	private Character DC = 'D'; // ?
	// 進/銷
	private Float TAMT = 0.0f;
	// 進銷金額
	private String MEMO = "";
	// 調撥理由

	private String[] values = { "VHNO", "FNO", "VHDT", "ANO", "DC", "TAMT", "MEMO" };
	private String[] types = { VHNO.getClass().getSimpleName(), FNO.getClass().getSimpleName(),
			VHDT.getClass().getSimpleName(), ANO.getClass().getSimpleName(), DC.getClass().getSimpleName(),
			TAMT.getClass().getSimpleName(), MEMO.getClass().getSimpleName() };
	private String primary = "VHNO";
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

	public String getVHNO() {
		return VHNO;
	}

	public void setVHNO(String vHNO) {
		VHNO = vHNO;
	}

	public String getFNO() {
		return FNO;
	}

	public void setFNO(String fNO) {
		FNO = fNO;
	}

	public int getVHDT() {
		return VHDT;
	}

	public void setVHDT(int vHDT) {
		VHDT = vHDT;
	}

	public String getANO() {
		return ANO;
	}

	public void setANO(String aNO) {
		ANO = aNO;
	}

	public char getDC() {
		return DC;
	}

	public void setDC(char dC) {
		DC = dC;
	}

	public float getTAMT() {
		return TAMT;
	}

	public void setTAMT(float tAMT) {
		TAMT = tAMT;
	}

	public String getMEMO() {
		return MEMO;
	}

	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}

}
