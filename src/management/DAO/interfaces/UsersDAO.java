package management.DAO.interfaces;

import management.DTO.UsersDTO;

public interface UsersDAO {
	/**
	 * 로그인
	 * @return UsersDTO
	 */
	public UsersDTO login(String userID, String userPassword);
	
	/**
	 * 회원가입
	 */
	public int register(String userID, String userPassword, String userName, String userBirth);
	
	/**
	 * 회원 정보 수정
	 */
	public int userUpdate(String userPassword);
	
	/**
	 * userID 기준으로 회원 정보 찾기
	 */
	public UsersDTO searchByUserID(String userID);

}
