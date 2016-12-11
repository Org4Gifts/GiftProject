package tw.youth.project.gift2016;

import javax.swing.JFrame;
import tw.youth.project.gift2016.info.SystemInfo;
import tw.youth.project.gift2016.sql.DBManager;
import tw.youth.project.gift2016.sql.SQLCmd;

public class PresentSystem extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DBManager dao;

	public static void main(String[] args) {
		new PresentSystem().setVisible(true);
	}

	public PresentSystem() {
		System.out.println("Initial frame");
		getContentPane().setLayout(null);
		setTitle(String.format("%s - %s", SystemInfo.SYSNAME, SystemInfo.VERSION));
		setBounds(SystemInfo.POINT_X, SystemInfo.POINT_Y, SystemInfo.WIDTH, SystemInfo.HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public synchronized boolean login(String name, String password) {
		System.out.println("Initial login");
		dao = new DBManager(SQLCmd.DB_URL, SQLCmd.DB_NAME, name, password);
		return dao.starup();
	}

	public synchronized boolean logout() {
		System.out.println("Initial logout");
		if (dao.getConn() != null) {
			dao.close();
		}
		return dao.getConn() == null;
	}

	public void close() {
		System.exit(0);
	}
}
