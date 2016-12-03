package tw.youth.project.gift2016.sql.avdr;

import java.sql.Timestamp;

public class AVDR {
	// 廠/客基本資料檔
	private Integer avdr_id = 0;

	private String cono = "";
	// 公司編號
	private String na = "";
	// 公司簡稱
	private String name = "";
	// 公司名稱
	private Integer id = 0;
	// 統一編號
	private String bose = "";
	// 負責人
	private String agent = "";
	// 業務代表
	private String title = "";
	// 職稱
	private String tel1 = "";
	// 電話1
	private String tel2 = "";
	// 電話2
	private String ptel = "";
	// 手機
	private String fax = "";
	// 傳真
	private String iadd = "";
	// 公司地址
	private String memo = "";
	// 備註
	private Timestamp created;
	private Timestamp updated;

	private String[] keys = { "avdr_id", "cono", "na", "name", "id", "bose", "agent", "title", "tel1", "tel2", "ptel",
			"fax", "iadd", "memo" };
	private String[] types = { avdr_id.getClass().getSimpleName(), cono.getClass().getSimpleName(),
			na.getClass().getSimpleName(), name.getClass().getSimpleName(), id.getClass().getSimpleName(),
			bose.getClass().getSimpleName(), agent.getClass().getSimpleName(), title.getClass().getSimpleName(),
			tel1.getClass().getSimpleName(), tel2.getClass().getSimpleName(), ptel.getClass().getSimpleName(),
			fax.getClass().getSimpleName(), iadd.getClass().getSimpleName(), memo.getClass().getSimpleName() };
	private String[] uniques = { "cono", "id" };

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
	public Integer getAvdr_id() {
		return avdr_id;
	}

	public void setAvdr_id(Integer avdr_id) {
		this.avdr_id = avdr_id;
	}

	public String getCono() {
		return cono;
	}

	public void setCono(String cono) {
		this.cono = cono;
	}

	public String getNa() {
		return na;
	}

	public void setNa(String na) {
		this.na = na;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBose() {
		return bose;
	}

	public void setBose(String bose) {
		this.bose = bose;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getPtel() {
		return ptel;
	}

	public void setPtel(String ptel) {
		this.ptel = ptel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIadd() {
		return iadd;
	}

	public void setIadd(String iadd) {
		this.iadd = iadd;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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
		setCono((String) values[i++]);
		setNa((String) values[i++]);
		setName((String) values[i++]);
		setId((Integer) values[i++]);
		setBose((String) values[i++]);
		setAgent((String) values[i++]);
		setTitle((String) values[i++]);
		setTel1((String) values[i++]);
		setTel2((String) values[i++]);
		setPtel((String) values[i++]);
		setFax((String) values[i++]);
		setIadd((String) values[i++]);
		setMemo((String) values[i++]);
	}

	public void setValuesFull(Object[] values) {
		int i = 0;
		setAvdr_id((Integer) values[i++]);
		setCono((String) values[i++]);
		setNa((String) values[i++]);
		setName((String) values[i++]);
		setId((Integer) values[i++]);
		setBose((String) values[i++]);
		setAgent((String) values[i++]);
		setTitle((String) values[i++]);
		setTel1((String) values[i++]);
		setTel2((String) values[i++]);
		setPtel((String) values[i++]);
		setFax((String) values[i++]);
		setIadd((String) values[i++]);
		setMemo((String) values[i++]);
		setCreated((Timestamp) values[i++]);
		setUpdated((Timestamp) values[i++]);
	}

	public Object[] getValues() {
		return new Object[] { getCono(), getNa(), getName(), getId(), getBose(), getAgent(), getTitle(), getTel1(),
				getTel2(), getPtel(), getFax(), getIadd(), getMemo() };
	}

	public Object[] getValuesFull() {
		return new Object[] { getAvdr_id(), getCono(), getNa(), getName(), getId(), getBose(), getAgent(), getTitle(),
				getTel1(), getTel2(), getPtel(), getFax(), getIadd(), getMemo(), getCreated(), getUpdated() };
	}

}
