package service;

import java.util.List;

import management.DTO.MovieDTO;
import management.DTO.SupportingActorDTO;

public interface SupportingActorService {
	/*
	 * SupportingActorDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 영화에 출연하는 조연 배우
	 */
	public List<SupportingActorDTO> selectSupportingActor(MovieDTO movie) /*throws SearchException*/;

}
