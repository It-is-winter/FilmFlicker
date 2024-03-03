package service.Impl;

import exception.SearchException;
import management.DTO.UsersDTO;
import service.UsersService;

public class UserServiceImpl implements UsersService {

	@Override
	public UsersDTO login(String userID, String userPassword) throws SearchException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(String userID, String userPassword, String userName, String userBirth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userUpdate(String userPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public void searchByUserID(String userID) {
		// TODO Auto-generated method stub

	}

}
