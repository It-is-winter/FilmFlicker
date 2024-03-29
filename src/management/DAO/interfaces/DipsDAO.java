package management.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import exception.DeleteException;
import exception.InsertException;
import exception.SearchException;
import management.DTO.DipsDTO;
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
	 * @throws SearchException 
	 */
	
	public Set<DipsDTO> selectDipsListAll(UsersDTO users) throws SearchException; //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 * @throws InsertException 
	 */
	public int insertDips(UsersDTO user, int movieSeq) throws InsertException;
	
	/**
	 * 찜 목록 삭제
	 * @throws DeleteException
	 */
	public int deleteDips(DipsDTO dips) throws DeleteException;

}
