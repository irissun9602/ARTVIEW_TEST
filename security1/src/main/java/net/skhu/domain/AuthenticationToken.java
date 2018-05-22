package net.skhu.domain;

public class AuthenticationToken {

	private String loginId;
	private String userType;

	private String token;

	public AuthenticationToken(String loginId, String userType, String token) {
		super();
		this.loginId = loginId;
		this.userType = userType;
		this.token = token;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
}
