package hw5;


public class User {
	private String userName;
	private String pass;
	private int id;

	public User(){
		
	}
	public User (User user){
		this.userName = user.getUserName();
		this.pass = user.getPass();
		this.id=user.getId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return ""+getId()+"-"+getUserName();
	}
}
