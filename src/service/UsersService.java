package service;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.UsersDTO;

public interface UsersService {
	/*
	 * UsersDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 로그인
	 * @return UsersDTO
	 * @throws SearchException 
	 */
	public UsersDTO login(String userID, String userPassword) throws SearchException;
	
	/**
	 * 회원가입
	 * @throws InsertException 
	 */
	public void register(String userID, String userPassword, String userName, String userBirth) throws InsertException;
	
	/**
	 * 회원 정보 수정
	 * @throws UpdateException 
	 */
	public void userUpdate(String userID,String userPassword) throws UpdateException;
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 * @return UsersDTO
	 * @throws SearchException 
	 */
	public UsersDTO searchByUserID(String userID) throws SearchException;

}
