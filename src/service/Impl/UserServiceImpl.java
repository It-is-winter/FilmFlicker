package service.Impl;

import java.sql.SQLException;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DAO.Impl.UsersDAOImpl;
import management.DAO.interfaces.UsersDAO;
import management.DTO.UsersDTO;
import service.UsersService;
import session.UserSession;
import session.UserSessionSet;

public class UserServiceImpl implements UsersService {

	UsersDAO userdao  = new UsersDAOImpl();
	
	@Override
	public UsersDTO login(String userID, String userPassword) throws SearchException, SQLException {
		UsersDTO user = userdao.login(userID, userPassword);
		
		if(user == null) {
			throw new SearchException("등록된 아이디가 없습니다.");
		}
		
		// user를 찾고 예외가 아니면 userSessionSet에 넣어서 관리
		UserSession userSession = new UserSession(userID);
		
		UserSessionSet userSessionSet = UserSessionSet.getInstance();
		userSessionSet.add(userSession);
		
		return user;
	}

	@Override
	public void register(String userID, String userPassword, String userName, String userBirth) throws InsertException, SQLException {
		int result = userdao.register(userID, userPassword, userName, userBirth);
		
		if(result == 0) {
			throw new InsertException("회원 가입이 실패했습니다.");
		}

	}

	@Override
	public void userUpdate(String userID,String userPassword)throws UpdateException, SQLException{
		int result = userdao.userUpdate(userID, userPassword);
		
		if(result == 0 ) {
			throw new UpdateException("정보 수정에 실패 했습니다.");
		}

	}

	@Override
	public UsersDTO searchByUserID(String userID) throws SearchException, SQLException {
		UsersDTO user = userdao.searchByUserID(userID);
		
		if(user == null) {
			throw new SearchException("등록된 아이디가 없습니다.");
		}
		
		return user;

	}

}
