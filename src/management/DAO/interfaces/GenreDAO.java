package management.DAO.interfaces;

import management.DTO.GenreDTO;

public interface GenreDAO {
	/**
	 * 영화 정보에 대한 장르 검색
	 */
	public GenreDTO searchGenre(int genreCode); //장르는 입력 받는게 아니라 이미 만들어진 테이블에서 선택
	
	/**
	 * 장르 코드 검색
	 */
	public int selectGenreCode(String genreName);

}
