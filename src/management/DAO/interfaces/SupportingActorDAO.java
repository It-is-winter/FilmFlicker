package management.DAO.interfaces;

import java.util.List;

import management.DTO.MovieDTO;
import management.DTO.SupportingActorDTO;

public interface SupportingActorDAO {
	/**
	 * 영화에 출연하는 조연 배우
	 */
	public List<SupportingActorDTO> selectSupportingActor(MovieDTO movie);

}
