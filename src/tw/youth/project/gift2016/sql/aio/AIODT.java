package tw.youth.project.gift2016.sql.aio;

public class AIODT {
	// 多廠別進/銷副檔

	private String vhno = ""; // auto
	// 單據編號
	private String fgno = "";
	// 禮品編號
	private Integer qty = 0;
	// 進銷數量
	private Float prc = 0.0f; // auto
	// 禮品單價
	private String order1 = "";
	// 訂單編號
	private String note1 = "";
	// 備註

	private String[] keys = { "vhno", "fgno", "qty", "prc", "order1", "note1" };
	private String[] types = { vhno.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			qty.getClass().getSimpleName(), prc.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			note1.getClass().getSimpleName() };
	private String[] uniques = { "" };
	private String primary = "vhno";

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
	//以下是儲存的值

	public String getVhno() {
		return vhno;
	}

	public void setVhno(String vhno) {
		this.vhno = vhno;
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

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}	

}
