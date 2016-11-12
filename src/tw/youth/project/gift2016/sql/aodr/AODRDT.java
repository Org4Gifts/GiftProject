package tw.youth.project.gift2016.sql.aodr;

import java.sql.Timestamp;

public class AODRDT {
	// 訂單副檔
	private Integer _id = 0;

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
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "order1", "fgno", "qty", "prc", "note1", "oqty" };
	private String[] types = { _id.getClass().getSimpleName(), order1.getClass().getSimpleName(),
			fgno.getClass().getSimpleName(), qty.getClass().getSimpleName(), prc.getClass().getSimpleName(),
			note1.getClass().getSimpleName(), oqty.getClass().getSimpleName() };
	private String[] uniques = { "order1" };

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

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

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
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setNote1((String) values[i++]);
		setOqty((Integer) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setOrder1((String) values[i++]);
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setNote1((String) values[i++]);
		setOqty((Integer) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getOrder1(), getFgno(), getQty(), getPrc(), getNote1(), getOqty() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getOrder1(), getFgno(), getQty(), getPrc(), getNote1(), getOqty(), getCreated(),
				getUpdated() };
	}

}
