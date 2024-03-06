package service;

import java.sql.SQLException;

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
	 * @throws SQLException 
	 */
	public UsersDTO login(String userID, String userPassword) throws SearchException, SQLException;
	
	/**
	 * 회원가입
	 * @throws SQLException 
	 * @throws SearchException 
	 */
	public void register(String userID, String userPassword, String userName, String userBirth) throws InsertException, SQLException, SearchException;
	
	/**
	 * 회원 정보 수정
	 * @throws SQLException 
	 */
	public void userUpdate(String userID,String userPassword) throws UpdateException, SQLException;
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 * @return 
	 * @throws SQLException 
	 * @throws SearchException 
	 */
	public UsersDTO searchByUserID(String userID) throws SearchException, SQLException ;

}
