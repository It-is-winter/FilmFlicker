package session;

import java.util.HashMap;
import java.util.Map;

public class UserSession {

	private String userSession;
	private Map<String, Object> attributes; // 찜목록
	
	
	public UserSession() {}
	public UserSession(String userSession) {
		this.userSession = userSession;
		this.attributes = new HashMap<>();
	}
	
	
	public String getUserSession() {
		return userSession;
	}
	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}
	
	
	// String name 에 따른 여러 map 테이블 만들기
	public void setAttribute(String name, Object value) {
		attributes.put(name,value);
	}
	
	// String name을 받아 여러 map 테이블 주기
	public Object getAttribute(String name) {
		return attributes.get(name);
	}
	
	// String name에 해당하는 map 테이블 삭제
	public void removeAttribute(String name) {//cart
		attributes.remove(name);
	}
	
	
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	@Override
	public int hashCode() {
		return userSession.hashCode() ;
	}
	
	@Override
	public boolean equals(Object obj) {
		UserSession other = (UserSession)obj;
		if(this.userSession.equals(other.getUserSession())) {
			return true;
		}else {
			return false;
		}

	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" ID = > ");
		builder.append(userSession);
		return builder.toString();
	}
	
	
	
}
	

