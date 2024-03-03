package controller;


import exception.SearchException;
import management.DTO.UsersDTO;
import service.UsersService;
import service.Impl.UserServiceImpl;
import view.FailView;
import view.MenuView;

public class UserController {

	public static UsersService userService = new UserServiceImpl();
	/**
	 * 
	 * 유저 아이디와 비번을 받아 유저를 반환
	 * @param userID
	 * @param userPassword
	 * @return UserDTO
	 */
	public static UsersDTO login(String userId, String userPassword) {
		UsersDTO user = null;
		
		try {
			user = userService.login(userId, userPassword);
			MenuView.printUserMenu(user);
		}catch (SearchException e) {
			e.printStackTrace();
			FailView.errorMessage(e.getMessage());
			
		}
		
		return user;
	}
}
