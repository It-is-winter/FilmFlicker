package controller;


import java.sql.SQLException;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.UsersDTO;
import service.UsersService;
import service.Impl.UserServiceImpl;
import view.FailView;
import view.MenuView;
import view.SuccessView;

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
			FailView.errorMessage(e.getMessage());
			
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
		return user;
	}
	
	/***
	 * 
	 * @param idEmail
	 * @param password
	 * @param name
	 * @param birth
	 * 
	 * idEmail , password , name , birth를 입력받아 UserDTO를 만든다.
	 */
	public static void register(String idEmail, String password, String name,String birth ) {
		
		try {
			userService.register(idEmail,password,name,birth);
			SuccessView.successMessage("회원 가입이 완료 되었습니다.");
		}catch (InsertException e) {
			FailView.errorMessage(e.getMessage());
		}catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		} catch (SQLException e) {	
			FailView.errorMessage(e.getMessage());
		}
	}
	
	/**
	 * 회원 정보 수정
	 * @param userID
	 * @param userPassword
	 */
	public static void userUpdate(String userID,String userPassword) {
		
		try {
			userService.userUpdate(userID, userPassword);
			SuccessView.successMessage("회원 수정이 완료되었습니다.");
		} catch (UpdateException e) {
			FailView.errorMessage(e.getMessage());
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 * @return 
	 * @throws SQLException 
	 * @throws SearchException 
	 */
	
	public static void searchByUserID(String userID) {
		
		try {
			UsersDTO user = userService.searchByUserID(userID);
			SuccessView.printPassword(user);
		} catch (SearchException e) {
			FailView.errorMessage(e.getMessage());
		}catch (SQLException e) {
			FailView.errorMessage(e.getMessage());
		}
		
	}
	
	
	
	
	
	
}
