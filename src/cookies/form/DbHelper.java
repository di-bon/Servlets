// Francesco Di Bon 5BIA 12-05-2022

package cookies.form;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://192.168.80.53:3306/userlogin";
	private static final String USER = "login";
	private static final String PASSWORD = "pippo";
	
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
	
	public boolean logon (String email, String password) throws SQLException {
		String query = "SELECT * FROM credentials WHERE email='" + email + "' AND pwd=md5('" + password + "');";
		Statement sql = conn.createStatement();
		ResultSet res = sql.executeQuery(query);
		return res.next();
	}
	
	public String getName(String email) throws SQLException {
		String query = "SELECT name FROM credentials WHERE email='" + email + "'";
		Statement statement = conn.createStatement();
		ResultSet res = statement.executeQuery(query);
		return res.next() ? res.getString("name") : "";
	}
}
