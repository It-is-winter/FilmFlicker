package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import exception.DeleteException;
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
	public Set<DipsDTO> selectDipsListAll(UsersDTO users) throws SearchException; //user 의 ID 를 받음
	
	/**
	 * 찜 목록 등록
	 */
	public int insertDips(UsersDTO users, int movieSeq) throws InsertException;
	
	/**
	 * 찜 목록 삭제
	 */
	public int deleteDips(DipsDTO dips) throws DeleteException;

}
