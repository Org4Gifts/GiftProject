package tw.youth.project.gift2016.sql.normal;
import java.sql.Timestamp;
public class Bulletin {

		// 系統公告用
		private Integer bulletin_id = 0;

		private String subject = "";
		// 廠別代碼 2碼
		private String content = "";
		// 廠別名稱
		private Timestamp created;
		private Timestamp updated;

		private String[] keys = { "bulletin_id", "subject", "content" };
		private String[] types = { bulletin_id.getClass().getSimpleName(), subject.getClass().getSimpleName(),
				content.getClass().getSimpleName() };
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

		public Integer getBulletin_id() {
			return bulletin_id;
		}

		public void setBulletin_id(Integer bulletin_id) {
			this.bulletin_id = bulletin_id;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
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
			setSubject((String) values[i++]);
			setContent((String) values[i++]);
		}

		public void setValuesFull(Object[] values) {
			int i = 0;
			setBulletin_id((Integer) values[i++]);
			setSubject((String) values[i++]);
			setContent((String) values[i++]);
			setCreated((Timestamp) values[i++]);
			setUpdated((Timestamp) values[i++]);
		}

		public Object[] getValues() {
			return new Object[] { getSubject(), getContent() };
		}

	public Object[] getValuesFull() {
			return new Object[] { getBulletin_id(),getSubject(),getContent(), getCreated(), getUpdated() };
		}
	}

