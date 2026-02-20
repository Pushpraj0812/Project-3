package in.co.rays.project_3.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class SessionDTO extends BaseDTO {

	private Long sessionId;
	private String sessionToken;
	private String userName;
	private String sessionStatus;
	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionStatus() {
		return sessionStatus;
	}

	public void setSessionStatus(String sessionStatus) {
		this.sessionStatus = sessionStatus;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	@Override
	public String getKey() {
		return sessionId + "";
	}

	@Override
	public String getValue() {
		return userName + "";
	}

}