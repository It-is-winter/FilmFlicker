package service;

import management.DTO.GenreDTO;

public interface GenreService {
	/*
	 * GenreDAO에 있는 기능 모두 호출, 예외 던져주기
	 */
	
	/**
	 * 영화 정보에 대한 장르 검색
	 */
	public GenreDTO searchGenre(int genreCode) /*throws SearchException*/; //장르는 입력 받는게 아니라 이미 만들어진 테이블에서 선택
	
	/**
	 * 장르 코드 검색
	 */
	public int selectGenreCode(String genreName) /*throws SearchException*/;

}
