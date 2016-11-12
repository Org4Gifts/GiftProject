package tw.youth.project.gift2016.sql.aio;

import java.sql.Timestamp;

public class AIODT {
	// 多廠別進/銷副檔
	private Integer _id = 0;

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
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "_id", "vhno", "fgno", "qty", "prc", "order1", "note1" };
	private String[] types = { _id.getClass().getSimpleName(), vhno.getClass().getSimpleName(),
			fgno.getClass().getSimpleName(), qty.getClass().getSimpleName(), prc.getClass().getSimpleName(),
			order1.getClass().getSimpleName(), note1.getClass().getSimpleName() };
	private String[] uniques = { "vhno" };

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
		setVhno((String) values[i++]);
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setNote1((String) values[i++]);
		setOrder1((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setVhno((String) values[i++]);
		setFgno((String) values[i++]);
		setQty((Integer) values[i++]);
		setNote1((String) values[i++]);
		setOrder1((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getVhno(), getFgno(), getQty(), getPrc(), getOrder1(), getNote1() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getVhno(), getFgno(), getQty(), getPrc(), getOrder1(), getNote1(), getCreated(),
				getUpdated() };
	}

}
