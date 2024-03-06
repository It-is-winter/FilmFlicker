package session;

import java.util.HashMap;
import java.util.Map;

public class UsersSession {
	private String sessionId; //usersId
	private Map<String, Object> attributes; //찜 목록, 리뷰 삭제, 비밀번호 수정
	
	public UsersSession() {}
	public UsersSession(String sessionId) {
		this.sessionId = sessionId;
		attributes = new HashMap<String, Object>();
	}
	/**
	 * 찜 목록, 리뷰 삭제, 비밀번호 수정
	 * */
	public void setAttribute(String name, Object value) {
		attributes.put(name, value); //<찜목록, 영화이름>, List<영화이름> //<내가 등록한 리뷰, 리뷰 내용>, List<리뷰 내용> //<비밀번호 변경, UsersDTO>
	}
	/**
	 * 찜 목록, 리뷰 삭제, 비밀번호 수정에 해당하는 value 찾기
	 * */
	public Object getAttribute(String name) {
		return attributes.get(name);
	}
	/**
	 * 되돌아가기
	 * */
	public void removeAttribute(String name) {
		attributes.remove(name);
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
