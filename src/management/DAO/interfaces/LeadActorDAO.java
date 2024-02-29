package management.DAO.interfaces;

import java.util.List;

import management.DTO.LeadActorDTO;
import management.DTO.MovieDTO;

public interface LeadActorDAO {
	/**
	 * 영화에 출연하는 주연 배우
	 */
	public List<LeadActorDTO> selectLeadActor(MovieDTO movie);

}
