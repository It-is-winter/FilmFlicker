package service.Impl;

import java.util.HashMap;
import java.util.Map;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.Impl.UsersDAOImpl;
import management.DAO.interfaces.UsersDAO;
import management.DTO.UsersDTO;
import service.UsersService;
import session.*;


public class UserServiceImpl implements UsersService {

	UsersDAO userdao  = new UsersDAOImpl();
	
	@Override
	public UsersDTO login(String userID, String userPassword) throws SearchException {
		UsersDTO user = userdao.login(userID, userPassword);
		
		if(searchByUserID(userID) == null) {
			throw new SearchException("등록된 정보가 없습니다.");
		} else {
			if(user.getPassword().equals(userPassword)) {
				// user를 찾고 예외가 아니면 userSessionSet에 넣어서 관리
				UsersSession userSession = new UsersSession(userID);
				
				UsersSessionSet userSessionSet = UsersSessionSet.getInstance();
				userSessionSet.add(userSession);
		
				Map<String, Object> myPage = new HashMap<String, Object>();
				userSession.setAttribute("마이페이지", myPage);
		
				return user;
			} else {
				throw new SearchException("비밀번호가 틀렸습니다.");
			}
		}
	}

	@Override
	public void register(String userID, String userPassword, String userName, String userBirth) throws InsertException {
		
		try {
			userdao.searchByUserID(userID);
		} catch (SearchException e) {
			int result = userdao.register(userID, userPassword, userName, userBirth);
			if(result == 0) {
				throw new InsertException("회원 가입이 실패했습니다.");
			}
		}
		
		
	}

	@Override
	public void userUpdate(String userID,String userPassword) throws UpdateException {
		int result = userdao.userUpdate(userID, userPassword);
		
		if(result == 0 ) {
			throw new UpdateException("정보 수정에 실패 했습니다.");
		}

	}

	@Override
	public UsersDTO searchByUserID(String userID) throws SearchException {
		UsersDTO user = userdao.searchByUserID(userID);
		if(user != null) {
			if(user.getIdEmail().equals(userID)) {
				return user;
			} else {
				throw new SearchException("등록된 아이디가 없습니다.");
			}
		} else {
			return null;
		}
	}

}
