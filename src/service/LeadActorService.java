package service;

import java.util.List;

import management.DTO.LeadActorDTO;
import management.DTO.MovieDTO;

public interface LeadActorService {
	/*
	 * LeadActorDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 영화에 출연하는 주연 배우
	 */
	public List<LeadActorDTO> selectLeadActor(MovieDTO movie) /*throws SearchException*/;

}
