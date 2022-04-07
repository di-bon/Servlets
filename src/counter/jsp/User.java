package counter.jsp;

import java.util.Date;

public class User {
	public Date date;
	public String address;
	public int port;
	
	public User(Date date, String address, int port) {
		this.date = date;
		this.address = address;
		this.port = port;
	}
//	public String toString() {
//		return ""+port;
//		
//	}
	public String getAddress() {
		return address;
	}
	public String getPort() {
		return port+"";
	}
	public String getDate() {
		return date.toString();
	}
}
