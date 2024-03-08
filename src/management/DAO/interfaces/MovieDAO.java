package management.DAO.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import exception.InsertException;
import exception.SearchException;
import management.DTO.MovieDTO;

public interface MovieDAO {
	/**
	 * 영화 등록
	 * @throws SQLException
	 */
	public int insertMovie( String movieName,int movieGenre, String movieDerector, String releaseDate ,List<String> leadActor,
			List<String> supportActor) throws InsertException;
	
	/**
	 * 장르로 영화 검색
	 */
	public List<MovieDTO> selectMovieByGenre(String movieGenre) throws SearchException;
	
	/**
	 * 감독으로 영화 검색
	 * @throws SQLException 
	 */
	public List<MovieDTO> selectMovieByDirecter(String movieDirecter) throws SearchException;
	
	/**
	 * 개봉연도로 영화 검색(배우 있는 경우)
	 */
	public Set<MovieDTO> selectMovieByReleaseDate(String releaseDate) throws SearchException;
	
	/**
	 * 이름으로 영화 검색
	 */
	public MovieDTO selectMovieByName(String movieName) throws SearchException;

	/**
	 * 개봉 연도로 영화찾기(배우 없는 경우)
	 */
	public Set<MovieDTO> selectMovieByReleaseDate(Connection con, String releaseDate) throws SearchException;

}
