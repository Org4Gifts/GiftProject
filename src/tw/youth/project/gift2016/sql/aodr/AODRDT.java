package tw.youth.project.gift2016.sql.aodr;

import java.sql.Timestamp;

public class AODRDT {
	// 訂單副檔
	private Integer aodrdt_id = 0;

	private String order1 = "";
	// 訂單編號
	private String comname = "";
	// 拜訪公司名稱
	private String pername = "";
	// 拜訪對象
	private Short authority = 0;
	// 拜訪職稱類別
	private String fgno = "";
	// 禮品代號
	private Integer qty = 0;
	// 禮品數量
	private Float prc = 0.0f;
	// 禮品單價
	private String note1 = "";
	// 備註
	// private Integer oqty = 0; // auto
	// // 已出貨量
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "aodrdt_id", "order1", "comname", "pername", "authority", "fgno", "qty", "prc", "note1",
			/* "oqty" */ };
	private String[] types = { aodrdt_id.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			comname.getClass().getSimpleName(), pername.getClass().getSimpleName(),
			authority.getClass().getSimpleName(), fgno.getClass().getSimpleName(), qty.getClass().getSimpleName(),
			prc.getClass().getSimpleName(), note1.getClass()
					.getSimpleName()/* , oqty.getClass().getSimpleName() */ };
	private String[] uniques = { "" };

	public String getTableName() {
		return getClass().getSimpleName().toLowerCase();
	}

	public String[] getKeys() {
		return keys;
	}

	public String[] getTypes() {
		return types;
	}

	public String[] getUniques() {
		return uniques;
	}

	public int getLength() {
		return getKeys().length + 2;
	}
	// 以下是儲存的值

	public Integer getAodrdt_id() {
		return aodrdt_id;
	}

	public void setAodrdt_id(Integer aodrdt_id) {
		this.aodrdt_id = aodrdt_id;
	}

	public String getOrder1() {
		return order1;
	}

	public void setOrder1(String order1) {
		this.order1 = order1;
	}

	public String getComname() {
		return comname;
	}

	public void setComname(String comname) {
		this.comname = comname;
	}

	public String getPername() {
		return pername;
	}

	public void setPername(String pername) {
		this.pername = pername;
	}

	public Short getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = Short.parseShort(Integer.toString(authority));
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

	// public Integer getOqty() {
	// return oqty;
	// }
	//
	// public void setOqty(Integer oqty) {
	// this.oqty = oqty;
	// }

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public void setValues(Object[] values) {
		int i = 0;
		setOrder1((String) values[i++]);
		setComname((String) values[i++]);
		setPername((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setNote1((String) values[i++]);
		// setOqty((Integer) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAodrdt_id((Integer) values[i++]);
		setOrder1((String) values[i++]);
		setComname((String) values[i++]);
		setPername((String) values[i++]);
		setAuthority((Integer) values[i++]);
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setNote1((String) values[i++]);
		// setOqty((Integer) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getOrder1(), getComname(), getPername(), getAuthority(), getFgno(), getQty(), getPrc(),
				getNote1()/* , getOqty() */ };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAodrdt_id(), getOrder1(), getComname(), getPername(), getAuthority(), getFgno(),
				getQty(), getPrc(), getNote1()/* , getOqty() */, getCreated(), getUpdated() };
	}

}
