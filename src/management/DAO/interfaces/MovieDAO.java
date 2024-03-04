package management.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import management.DTO.MovieDTO;

public interface MovieDAO {
	/**
	 * 영화 등록
	 * 
	 */
	public int insertMovie( String movieName, String movieDerector, String releaseDate ,List<String> leadActor,
			List<String> supportActor) throws InsertException;
	
	/**
	 * 장르로 영화 검색
	 */
	public List<MovieDTO> selectMovieByGenre(String movieGenre);
	
	/**
	 * 감독으로 영화 검색
	 */
	public List<MovieDTO> selectMovieByDerector(String movieDerector);
	
	/**
	 * 개봉연도로 영화 검색
	 */
	public List<MovieDTO> selectMovieByReleaseDate(String releaseDate);
	
	/**
	 * 이름으로 영화 검색
	 * 
	 */
	public MovieDTO selectMovieByName(String movieName) throws SQLException;

}
