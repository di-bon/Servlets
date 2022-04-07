package counter.jsp;

import java.util.Date;

public class User {
	public Date date;
	public String address;
	public int port;
	public int id;
	
	public User(Date date, String address, int port, int id) {
		this.date = date;
		this.address = address;
		this.port = port;
		this.id = id;
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
	public String getId() {
		return ""+id;
	}
}
