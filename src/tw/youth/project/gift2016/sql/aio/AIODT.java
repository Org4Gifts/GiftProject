package tw.youth.project.gift2016.sql.aio;

public class AIODT {
	// 多廠別進/銷副檔

	private String VHNO = ""; // auto
	// 單據編號
	private String FGNO = "";
	// 禮品編號
	private Integer QTY = 0;
	// 進銷數量
	private Float PRC = 0.0f; // auto
	// 禮品單價
	private String ORDER1 = "";
	// 訂單編號
	private String NOTE1 = "";
	// 備註

	private String[] values = { "VHNO", "FGNO", "QTY", "PRC", "ORDER1", "NOTE1" };
	private String[] types = { VHNO.getClass().getSimpleName(), FGNO.getClass().getSimpleName(),
			QTY.getClass().getSimpleName(), PRC.getClass().getSimpleName(), ORDER1.getClass().getSimpleName(),
			NOTE1.getClass().getSimpleName() };
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

	public String getFGNO() {
		return FGNO;
	}

	public void setFGNO(String fGNO) {
		FGNO = fGNO;
	}

	public int getQTY() {
		return QTY;
	}

	public void setQTY(int qTY) {
		QTY = qTY;
	}

	public float getPRC() {
		return PRC;
	}

	public void setPRC(float pRC) {
		PRC = pRC;
	}

	public String getORDER1() {
		return ORDER1;
	}

	public void setORDER1(String oRDER1) {
		ORDER1 = oRDER1;
	}

	public String getNOTE1() {
		return NOTE1;
	}

	public void setNOTE1(String nOTE1) {
		NOTE1 = nOTE1;
	}

}
