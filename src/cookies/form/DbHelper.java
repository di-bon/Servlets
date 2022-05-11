package cookies.form;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/loginator";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	private Connection conn;
	
	public DbHelper() {
		super();
		conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException cnfe) {
			
		}
	}
	
	public void connect() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	}
	
	public void disconnect() throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
	public boolean logon (String username, String password) throws SQLException {
		String query = "SELECT * FROM users WHERE username='" + username + "' AND password=md5('" + password + "');";
		Statement sql = conn.createStatement();
		ResultSet res = sql.executeQuery(query);
		return res.next();
	}
}
