package tw.youth.project.gift2016.sql.aio;

import java.sql.Date;

public class AIODT {
	// 多廠別進/銷副檔
	private Integer _id = 0;

	private String vhno = ""; // auto
	// 單據編號
	private String inno = "";
	// 轉入禮品編號
	private String outno = "";
	// 轉入禮品編號
	private Integer qty = 0;
	// 進銷數量
	private Float prc = 0.0f; // auto
	// 禮品單價
	private String order1 = "";
	// 訂單編號
	private String note1 = "";
	// 備註
	private Date created;
	private Date updated;

	private String[] keys = { "_id", "vhno", "inno", "outno", "qty", "prc", "order1", "note1" };
	private String[] types = { _id.getClass().getSimpleName(), vhno.getClass().getSimpleName(),
			inno.getClass().getSimpleName(), outno.getClass().getSimpleName(), qty.getClass().getSimpleName(),
			prc.getClass().getSimpleName(), order1.getClass().getSimpleName(), note1.getClass().getSimpleName() };
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

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public String getVhno() {
		return vhno;
	}

	public void setVhno(String vhno) {
		this.vhno = vhno;
	}

	public String getInno() {
		return inno;
	}

	public void setInno(String inno) {
		this.inno = inno;
	}

	public String getOutno() {
		return outno;
	}

	public void setOutno(String outno) {
		this.outno = outno;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setValues(Object[] values) {
		int i = 0;
		setVhno((String) values[i++]);
		setInno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setOrder1((String) values[i++]);
		setNote1((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setVhno((String) values[i++]);
		setInno((String) values[i++]);
		setQty((Integer) values[i++]);
		setPrc((Float) values[i++]);
		setOrder1((String) values[i++]);
		setNote1((String) values[i++]);
		setCreated((Date) values[i++]);
		setUpdated((Date) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getVhno(), getInno(), getQty(), getPrc(), getOrder1(), getNote1() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getVhno(), getInno(), getQty(), getPrc(), getOrder1(), getNote1(), getCreated(),
				getUpdated() };
	}

}
