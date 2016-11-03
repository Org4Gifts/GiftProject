package tw.youth.project.gift2016.sql.aodr;

public class AODRDT {
	// 訂單副檔

	private String order1 = "";
	// 訂單編號
	private String fgno = "";
	// 產品代號
	private Integer qty = 0;
	// 產品數量
	private Float prc = 0.0f;
	// 產品單價
	private String note1 = "";
	// 備註
	private Integer oqty = 0; // auto
	// 已出貨量

	private String[] keys = { "order1", "fgno", "qty", "prc", "note1", "oqty" };
	private String[] types = { order1.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			qty.getClass().getSimpleName(), prc.getClass().getSimpleName(), note1.getClass().getSimpleName(),
			oqty.getClass().getSimpleName() };
	private String[] uniques = { "" };
	private String primary = "order1";

	public String getTableName() {
		return getClass().getSimpleName();
	}

	public String[] getKeys() {
		return keys;
	}

	public String[] getTypes() {
		return types;
	}

	public String getPrimary() {
		return primary;
	}

	public String[] getUniques() {
		return uniques;
	}
	// 以下是儲存的值

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Float getPrc() {
		return prc;
	}

	public void setPrc(Float prc) {
		this.prc = prc;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public Integer getOqty() {
		return oqty;
	}

	public void setOqty(Integer oqty) {
		this.oqty = oqty;
	}

}
