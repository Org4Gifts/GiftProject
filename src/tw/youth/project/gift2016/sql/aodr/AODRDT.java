package tw.youth.project.gift2016.sql.aodr;

public class AODRDT {
	// 訂單副檔

	private String ORDER1 = "";
	// 訂單編號
	private String FGNO = "";
	// 產品代號
	private Integer QTY = 0;
	// 產品數量
	private Float PRC = 0.0f;
	// 產品單價
	private String NOTE1 = "";
	// 備註
	private Integer OQTY = 0; // auto
	// 已出貨量

	private String[] values = { "ORDER1", "FGNO", "QTY", "PRC", "NOTE1", "OQTY" };
	private String[] types = { ORDER1.getClass().getSimpleName(), FGNO.getClass().getSimpleName(),
			QTY.getClass().getSimpleName(), PRC.getClass().getSimpleName(), NOTE1.getClass().getSimpleName(),
			OQTY.getClass().getSimpleName() };
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
	// 以下是儲存的值

	public String getORDER1() {
		return ORDER1;
	}

	public void setORDER1(String oRDER1) {
		ORDER1 = oRDER1;
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

	public String getNOTE1() {
		return NOTE1;
	}

	public void setNOTE1(String nOTE1) {
		NOTE1 = nOTE1;
	}

	public int getOQTY() {
		return OQTY;
	}

	public void setOQTY(int oQTY) {
		OQTY = oQTY;
	}

}
