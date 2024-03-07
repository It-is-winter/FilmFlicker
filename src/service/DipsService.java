package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import exception.SearchException;
import management.DTO.DipsDTO;
import management.DTO.UsersDTO;

public interface DipsService {
	/*
	 * DipsDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 찜 목록 조회
	 * @throws SearchException 
	 * @throws SQLException 
	 */
	public List<DipsDTO> selectDipsListAll(UsersDTO users) throws SearchException, SQLException; //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 * @throws InsertException 
	 * @throws SQLException 
	 */
	public int insertDips(UsersDTO user, int movieSeq) throws InsertException, SQLException;
	

	
	/**
	 * 찜 목록 삭제
	 */
	public int deleteDips(UsersDTO users) /*throws DeleteException*/;

	/*
	 * 감독이름으로 찜 등록
	 */
	
	public int insertDirectorDips(UsersDTO user) throws IOException, SQLException;

	
	
	
	
	
}
