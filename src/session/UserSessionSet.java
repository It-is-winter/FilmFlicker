package session;

import java.util.HashSet;
import java.util.Set;


public class UserSessionSet {

	private static UserSessionSet ss = new UserSessionSet();
	private Set<UserSession> userSet;
	
	private UserSessionSet() {
		userSet = new HashSet();
	}
	
	public static UserSessionSet getInstance() {
		return ss;
	}

	
	// 로그인 관리 set 반환
	public Set<UserSession> getUserSet() {
		return this.userSet;
	}

	// 로그인 시 set에 추가
	public void add(UserSession userSession) {
		userSet.add(userSession);
	}
	
	
	// 로그 아웃 시 set에 제거
	public void remove(UserSession userSession) {
		userSet.remove(userSession);
	}
	
}
