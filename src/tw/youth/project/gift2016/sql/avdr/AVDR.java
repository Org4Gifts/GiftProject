package tw.youth.project.gift2016.sql.avdr;

public class AVDR {
	// 廠/客基本資料檔

	private String cono = "";
	// 公司編號
	private String na = "";
	// 公司簡稱
	private String name = "";
	// 公司名稱
	private String id = "";
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

	private String[] keys = { "cono", "na", "name", "id", "bose", "agent", "title", "tel1", "tel2", "ptel", "fax",
			"iadd", "memo" };
	private String[] types = { cono.getClass().getSimpleName(), na.getClass().getSimpleName(),
			name.getClass().getSimpleName(), id.getClass().getSimpleName(), bose.getClass().getSimpleName(),
			agent.getClass().getSimpleName(), title.getClass().getSimpleName(), tel1.getClass().getSimpleName(),
			tel2.getClass().getSimpleName(), ptel.getClass().getSimpleName(), fax.getClass().getSimpleName(),
			iadd.getClass().getSimpleName(), memo.getClass().getSimpleName() };
	private String[] uniques = { "id" };
	private String primary = "cono";


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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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


	
}
