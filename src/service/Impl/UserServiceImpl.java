package service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.Impl.UsersDAOImpl;
import management.DAO.interfaces.UsersDAO;
import management.DTO.UsersDTO;
import service.UsersService;
import session.UsersSession;
import session.UsersSessionSet;


public class UserServiceImpl implements UsersService {

	UsersDAO userdao  = new UsersDAOImpl();
	private int adultAge = 19;
	LocalDate now = LocalDate.now();
	
	@Override
	public UsersDTO login(String userID, String userPassword) throws SearchException {
		
		
		if(this.searchByUserID(userID) == null) {
			throw new SearchException("아이디가 올바르지 않습니다.");
		}
		
		if(this.searchByUserPassword(userPassword) == null) {
			throw new SearchException("비밀번호가 올바르지 않습니다.");
		}
		
		UsersDTO user = userdao.login(userID, userPassword);
		
		if(user == null) {
			throw new SearchException("등록된 아이디가 없습니다.");
		}
		// user를 찾고 예외가 아니면 userSessionSet에 넣어서 관리
		UsersSession userSession = new UsersSession(userID);
		
		UsersSessionSet userSessionSet = UsersSessionSet.getInstance();
		userSessionSet.add(userSession);
		
		Map<String, Object> myPage = new HashMap<String, Object>();
		userSession.setAttribute("마이페이지", myPage);
		
		return user;
	}

	@Override
	public void register(String userID, String userPassword, String userName, String userBirth) throws InsertException, SearchException {
		
		
		try {
			userdao.searchByUserID(userID);
		} catch (SearchException e) {
			if( now.getYear() - Integer.parseInt(this.replaceBirth(userBirth))  < adultAge ) {
				throw new InsertException("미성년자는 회원가입에 제한됩니다.");
			}
			
			int result = userdao.register(userID, userPassword, userName, userBirth);
			
			if(result == 0) {
				throw new InsertException("회원 가입이 실패했습니다.");
			}
		}
	}

	@Override
	public void userUpdate(String userID,String userPassword)throws UpdateException{
		int result = userdao.userUpdate(userID, userPassword);
		
		if(result == 0 ) {
			throw new UpdateException("정보 수정에 실패 했습니다.");
		}

	}

	@Override
	public UsersDTO searchByUserID(String userID) throws SearchException{
		UsersDTO user = userdao.searchByUserID(userID);
		
		if(user == null) {
			throw new SearchException("잘못된 아이디 입니다..");
		}
		
		return user;

	}
	
	@Override
	public UsersDTO searchByUserPassword(String userPassword) throws SearchException {
		UsersDTO user = userdao.searchByUserPassword(userPassword);
		
		if(user == null) {
			throw new SearchException("패스워드가 다릅니다..");
		}
		
		return user;
	}
	
	public String replaceBirth(String userBirth) throws InsertException {
		String userYear = null;
		try {
			SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy");
			Date Year = dtFormat.parse(userBirth);
			userYear = dtFormat.format(Year);
		} catch (ParseException  e) {
			throw new InsertException("생년월일의 형식이 맞지 않습니다.");
		}
		return userYear;
	}

}
