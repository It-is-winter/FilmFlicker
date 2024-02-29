package service;

import java.util.List;

import management.DTO.MovieDTO;
import management.DTO.UsersDTO;

public interface DipsService {
	/*
	 * DipsDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 찜 목록 조회
	 */
	public List<MovieDTO> selectDipsListAll(UsersDTO users) /*throws SearchException*/; //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 */
	public int insertDips(UsersDTO users) /*throws InsertException*/;
	
	/**
	 * 찜 목록 삭제
	 */
	public int deleteDips(UsersDTO users) /*throws DeleteException*/;

}
