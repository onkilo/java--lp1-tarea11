package util;

import java.sql.*;

public class Conexion {
	private static String bd = "BDPapeleta";
	private static String user = "sa";
	private static String pass = "sql";
	private static String url = "jdbc:sqlserver://localhost:1433;databaseName=" + bd;
	
	public static Connection conectar(){
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
