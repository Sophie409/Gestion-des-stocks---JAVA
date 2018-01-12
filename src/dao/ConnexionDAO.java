package dao;

import java.sql.*;

public class ConnexionDAO {

	private static Connection cn;

	private static Statement st;
	
	public static void connexion() {
		try {
			Class.forName("oracle.jdbc.driver.OrcaleDriver");
			cn = DriverManager.getConnection("jdbc:oracle:thin:@gloin:1521:iut", "cayuelas", "1108009729B");
			st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getCn() {
		if (cn == null) {
			connexion();
		}
		return cn;
	}
	
	public static Statement getStatement() {
		if (st == null) {
			connexion();
		}
		return st;
	}
	
}
