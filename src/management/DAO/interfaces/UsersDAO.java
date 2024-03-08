package management.DAO.interfaces;

import exception.InsertException;
import exception.SearchException;
import exception.UpdateException;
import management.DTO.UsersDTO;

public interface UsersDAO {
	/**
	 * 로그인
	 * @return UsersDTO
	 * userid 를 uniq로 하여 중복 안되게 한다.
	 * select * from users where user_ID = ? and USER_password ?
	 * @throws SearchException 
	 */
	public UsersDTO login(String userID, String userPassword) throws SearchException;
	
	/**
	 * 회원가입
	 * insert into users values(user_seq_NO.NEXTVAL,?,?,?,?,sysdate)
	 * @throws InsertException
	 */
	public int register(String userID, String userPassword, String userName, String userBirth) throws InsertException;
	
	/**
	 * 회원 정보 수정
	 * update users set USER_password = ? where user_ID = ?
	 * @throws UpdateException 
	 */
	public int userUpdate(String userID,String userPassword)throws UpdateException;
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 * select * from users where user_ID = ?
	 * @throws SearchException 
	 */
	public UsersDTO searchByUserID(String userID)throws SearchException;

}
