package entity;

public class User {

	private int ID; 
	private String userName;
	private String password;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(int iD, String userName, String password) {
		super();
		ID = iD;
		this.userName = userName;
		this.password = password;
	}
	
}
