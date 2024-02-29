package management.DAO.interfaces;

import java.util.List;

import management.DTO.MovieDTO;
import management.DTO.UsersDTO;

public interface DipsDAO {
	/**
	 * 찜 목록 조회
	 */
	public List<MovieDTO> selectDipsListAll(UsersDTO users); //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 */
	public int insertDips(UsersDTO users);
	
	/**
	 * 찜 목록 삭제
	 */
	public int deleteDips(UsersDTO users);

}
