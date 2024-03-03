package management.DAO.Impl;

import java.util.List;

import management.DAO.interfaces.ActorDAO;
import management.DTO.ActorDTO;
import management.DTO.MovieDTO;

public class ActorDAOImpl implements ActorDAO{
	
	/**
	 * 배우 등록
	 */
	@Override
	public int insertActor(String actorName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 해당 영화에 출연한 배우 검색
	 */
	@Override
	public List<ActorDTO> selectActor(MovieDTO movie) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
