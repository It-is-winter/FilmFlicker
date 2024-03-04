package session;

import java.util.HashSet;
import java.util.Set;

public class UsersSessionSet {
	private static UsersSessionSet usersSessionSet = new UsersSessionSet();
	private Set<UsersSession> set;
	
	private UsersSessionSet() {
		set = new HashSet<UsersSession>();
	}
	
	public static UsersSessionSet getInstance() {
		return usersSessionSet;
	}
	
	/**
	 * 사용자 찾기
	 * */
	public UsersSession get(String sessionId) {
		for(UsersSession s : set) {
			if(s.getSessionId().equals(sessionId)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * 로그인된 사용자 추가
	 * */
	public void add(UsersSession usersSession) {
		set.add(usersSession);
	}
	
	/**
	 * 로그아웃한 사용자 제거
	 * */
	public void remove(UsersSession usersSession) {
		set.remove(usersSession);
	}

}
