package org.eop.jedis.test;

/**
 * @author lixinjie
 * @since 2017-06-14
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 6445285526408673647L;
	private String userName;
	private String password;
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
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

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}
	
}
