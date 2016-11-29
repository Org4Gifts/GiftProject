package tw.youth.project.gift2016.sql.aqty;

import java.sql.Date;
import java.sql.Timestamp;

public class AQTY {
	// 多廠別進/銷彙總檔
	private Integer _id = 0;

	private Integer yymm = 10511; // auto
	// 資料年月 紀錄型態如同初始值 所以使用int
	private String fno = ""; // auto
	// 廠區代碼 2碼
	private String fgno = ""; // auto
	// 禮品編號
	private Integer pmqty = 0; // auto
	// 期初數量
	private Integer inqty = 0; // auto
	// 本月進貨
	private Integer udqty = 0; // auto
	// 本月銷貨
	private Date created;
	private Date updated;

	private String[] keys = { "_id", "yymm", "fno", "fgno", "pmqty", "inqty", "udqty" };
	private String[] types = { _id.getClass().getSimpleName(), yymm.getClass().getSimpleName(),
			fno.getClass().getSimpleName(), fgno.getClass().getSimpleName(), pmqty.getClass().getSimpleName(),
			inqty.getClass().getSimpleName(), udqty.getClass().getSimpleName() };
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

	public Integer getYymm() {
		return yymm;
	}

	public void setYymm(Integer yymm) {
		this.yymm = yymm;
	}

	public String getFno() {
		return fno;
	}

	public void setFno(String fno) {
		this.fno = fno;
	}

	public String getFgno() {
		return fgno;
	}

	public void setFgno(String fgno) {
		this.fgno = fgno;
	}

	public Integer getPmqty() {
		return pmqty;
	}

	public void setPmqty(Integer pmqty) {
		this.pmqty = pmqty;
	}

	public Integer getInqty() {
		return inqty;
	}

	public void setInqty(Integer inqty) {
		this.inqty = inqty;
	}

	public Integer getUdqty() {
		return udqty;
	}

	public void setUdqty(Integer udqty) {
		this.udqty = udqty;
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
		setYymm((Integer) values[i++]);
		setFno((String) values[i++]);
		setFgno((String) values[i++]);
		setPmqty((Integer) values[i++]);
		setInqty((Integer) values[i++]);
		setUdqty((Integer) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		set_id((Integer) values[i++]);
		setYymm((Integer) values[i++]);
		setFno((String) values[i++]);
		setFgno((String) values[i++]);
		setPmqty((Integer) values[i++]);
		setInqty((Integer) values[i++]);
		setUdqty((Integer) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getYymm(), getFno(), getFgno(), getPmqty(), getInqty(), getUdqty() };
	}

	public Object[] getValuesFull() {
		return new Object[] { get_id(), getYymm(), getFno(), getFgno(), getPmqty(), getInqty(), getUdqty(),
				getCreated(), getUpdated() };
	}
}
