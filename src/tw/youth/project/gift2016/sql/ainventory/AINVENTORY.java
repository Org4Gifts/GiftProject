package tw.youth.project.gift2016.sql.ainventory;

import java.sql.Date;
import java.sql.Timestamp;

public class AINVENTORY {
	// 多廠別盤存檔
	private Integer _id = 0;

	private String invo = "";
	// 盤點單號
	private String fno = "";
	// 儲存廠別 2碼
	private Integer yymm = 10511;
	// 資料年月 紀錄型態如同初始值 所以使用int
	private String fgno = "";
	// 禮品編號
	private Integer ivqty = 0;
	// 盤點數量
	private Integer sqty = 0; // auto
	// 系統數量
	private Date created;
	private Date updated;

	private String[] keys = { "_id", "invo", "fno", "yymm", "fgno", "ivqty", "sqty" };
	private String[] types = { _id.getClass().getSimpleName(), invo.getClass().getSimpleName(),
			fno.getClass().getSimpleName(), yymm.getClass().getSimpleName(), fgno.getClass().getSimpleName(),
			ivqty.getClass().getSimpleName(), sqty.getClass().getSimpleName() };
	private String[] uniques = { "invo" };

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

	public String getInvo() {
		return invo;
	}

	public void setInvo(String invo) {
		this.invo = invo;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public Integer getYymm() {
		return yymm;
	}

	public void setYymm(Integer yymm) {
		this.yymm = yymm;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public Integer getIvqty() {
		return ivqty;
	}

	public void setIvqty(Integer ivqty) {
		this.ivqty = ivqty;
	}

	public Integer getSqty() {
		return sqty;
	}

	public void setSqty(Integer sqty) {
		this.sqty = sqty;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = new Date(created.getTime());
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = new Date(updated.getTime());
	}

	public void setValues(Object[] values) {
		int i = 0;
		setInvo((String) values[i++]);
		setFno((String) values[i++]);
		setYymm((Integer) values[i++]);
		setFgno((String) values[i++]);
		setIvqty((Integer) values[i++]);
		setSqty((Integer) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setInvo((String) values[i++]);
		setFno((String) values[i++]);
		setYymm((Integer) values[i++]);
		setFgno((String) values[i++]);
		setIvqty((Integer) values[i++]);
		setSqty((Integer) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getInvo(), getFno(), getYymm(), getFgno(), getIvqty(), getSqty() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getInvo(), getFno(), getYymm(), getFgno(), getIvqty(), getSqty(), getCreated(),
				getUpdated() };
	}
}
