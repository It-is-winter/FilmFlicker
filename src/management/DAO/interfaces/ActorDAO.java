package management.DAO.interfaces;

import java.util.List;

import management.DTO.ActorDTO;
import management.DTO.MovieDTO;

public interface ActorDAO {
	
	/**
	 * 배우 등록
	 */
	public int insertActor(String actorName);
	
	/**
	 * 해당 영화에 출연한 배우 검색
	 */
	public List<ActorDTO> selectActor(MovieDTO movie);
	
}
