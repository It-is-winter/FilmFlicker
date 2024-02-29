package service;

import management.DTO.UsersDTO;

public interface UsersService {
	/*
	 * UsersDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 로그인
	 * @return UsersDTO
	 */
	public void login(String userID, String userPassword) /*throws SearchException*/;
	
	/**
	 * 회원가입
	 */
	public void register(String userID, String userPassword, String userName, String userBirth) /*throws InsertException*/;
	
	/**
	 * 회원 정보 수정
	 */
	public void userUpdate(String userPassword) /*throws UpdateException, SearchException*/;
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 */
	public void searchByUserID(String userID) /*throws SearchException*/;

}
