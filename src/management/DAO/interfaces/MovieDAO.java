package management.DAO.interfaces;

import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import management.DTO.MovieDTO;

public interface MovieDAO {
	/**
	 * 영화 등록
	 * @throws SQLException 
	 * 
	 */
	public int insertMovie( String movieName,int movieGenre, String movieDerector, String releaseDate ,List<String> leadActor,
			List<String> supportActor) throws InsertException;
	
	/**
	 * 장르로 영화 검색
	 */
	public List<MovieDTO> selectMovieByGenre(String movieGenre) throws SQLException;
	
	/**
	 * 감독으로 영화 검색
	 * @throws SQLException 
	 */
	public List<MovieDTO> selectMovieByDirector(String movieDirector) throws SQLException;
	
	/**
	 * 개봉연도로 영화 검색
	 */
	public List<MovieDTO> selectMovieByReleaseDate(String releaseDate) throws SQLException;
	
	/**
	 * 이름으로 영화 검색
	 * 
	 */
	public MovieDTO selectMovieByName(String movieName) throws SQLException;

}
