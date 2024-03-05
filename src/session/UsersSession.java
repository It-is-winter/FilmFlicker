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
	
	
	// String name 에 따라 map 추가
	public void setAttribute(String name, Object value) {
		attributes.put(name,value);
	}
	
	// String name을 받아 map 만들기
	public Object getAttribute(String name) {//cart
		return attributes.get(name);
	}
	
	// String name에 해당하는 map 제거
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersSession ID =");
		builder.append(sessionId);
		return builder.toString();
	}
	
	

}
