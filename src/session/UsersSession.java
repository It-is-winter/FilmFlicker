package session;

import java.util.HashMap;
import java.util.Map;

public class UsersSession {
	private String sessionId; //usersId
	private Map<String, Object> attributes; //찜 목록, 리뷰 삭제
	
	public UsersSession() {}
	public UsersSession(String sessionId) {
		this.sessionId = sessionId;
		attributes = new HashMap<String, Object>();
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	@Override
	public int hashCode() {
		return sessionId.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		UsersSession other = (UsersSession) obj;
		if(sessionId.equals(other.sessionId)) {
			return true;
		}
		return false;
	}

}
