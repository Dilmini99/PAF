package net.usermanagement.model;

public class User {
	protected int userid;
	protected String username;
	protected String password;
	protected String employeeID;
	
	public User() {
	}
	
	public User(String username, String password, String employeeID) {
		super();
		this.username = username;
		this.password = password;
		this.employeeID = employeeID;
	}

	public User(int userid, String username, String password, String employeeID) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.employeeID = employeeID;
	}

	public int getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
		
	
}
		
