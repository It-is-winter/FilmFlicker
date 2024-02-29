package management.DAO.Impl;

import management.DAO.interfaces.UsersDAO;
import management.DTO.UsersDTO;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public UsersDTO login(String userID, String userPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int register(String userID, String userPassword, String userName, String userBirth) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int userUpdate(String userPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UsersDTO searchByUserID(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

}
