package management.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.MovieDTO;
import management.DTO.UsersDTO;

public interface DipsDAO {
	/**
	 * 찜 목록 조회
	 * movie와 dips를 합친 view를 만듬.
	 * create or replace view view_dips_info
		as select d2.movie_name,d2.movie_director,d2.move_genre, d1.user_seq from dips d1 join movie d2
		on d1.movie_seq = d2.movie_seq;
		이 뷰에서 user_seq가 맞는거 뽑아내기.
		select * from view_dips_info
	 * @throws SQLException 
	 * @throws SearchException 
	 */
	
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SQLException, SearchException; //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 */
	public int insertDips(UsersDTO users);
	
	/**
	 * 찜 목록 삭제
	 */
	public int deleteDips(UsersDTO users);

}
