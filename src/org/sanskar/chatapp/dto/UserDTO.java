package org.sanskar.chatapp.dto;

public class UserDTO {
	private String userID;
	private char[] password;
	
	public UserDTO(String userID,char[] password) {
		this.userID = userID;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}
	
}
