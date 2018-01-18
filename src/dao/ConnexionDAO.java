package dao;

import java.sql.*;

public class ConnexionDAO {

	private static ConnexionDAO instance = null;
	
	private Connection cn;
	
	private Statement st;
	
	private ConnexionDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@162.38.222.149:1521:iut", "cayuelas", "1108009729B");
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		return this.cn;
	}

	public Statement getStatement() {
		return this.st;
	}
	
	public synchronized static ConnexionDAO getInstance() {
		if (instance == null) {
			instance = new ConnexionDAO();
		}
		return instance;
	}
	
	
//	private static Connection cn;
//
//	private static Statement st;
//	
//	public static void connexion() {
//		try {
//			Class.forName("oracle.jdbc.driver.OrcaleDriver");
//			cn = DriverManager.getConnection("jdbc:oracle:thin:@gloin:1521:iut", "cayuelas", "1108009729B");
//			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public Connection getCn() {
//		if (cn == null) {
//			connexion();
//		}
//		return cn;
//	}
//	
//	public static Statement getStatement() {
//		if (st == null) {
//			connexion();
//		}
//		return st;
//	}
	
}
