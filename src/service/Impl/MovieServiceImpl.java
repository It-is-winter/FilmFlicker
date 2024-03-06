package service.Impl;

import java.sql.SQLException;
import java.util.List;

import exception.InsertException;
import management.DAO.Impl.MovieDAOImpl;
import management.DAO.interfaces.MovieDAO;
import management.DTO.MovieDTO;
import service.MovieService;

public class MovieServiceImpl implements MovieService {
	MovieDAO moviedao = new MovieDAOImpl();

	@Override
	public int insertMovie(String movieName,int movieGenre, String movieDirecter, String releaseDate, List<String> leadActor,
			List<String> supportActor) throws InsertException {
		
		int result = moviedao.insertMovie( movieName,movieGenre,  movieDirecter,  releaseDate, leadActor, supportActor);
		return result;
	
	}

	@Override
	public List<MovieDTO> selectMovieByGenre(String movieGenre) throws SQLException{
		List<MovieDTO> moviedto = moviedao.selectMovieByGenre(movieGenre);
		return moviedto;
	}

	@Override
	public List<MovieDTO> selectMovieByDirecter(String movieDirecter) throws SQLException {
		
		List<MovieDTO> moviedto = moviedao.selectMovieByDirecter(movieDirecter);
		
		
		//if(moviedto ==null) throw new SQLException("찾는 감독이 없습니다");
	
		return moviedto;
	}

	@Override
	public List<MovieDTO> selectMovieByReleaseDate(String movieReleaseDate) throws SQLException {
		List<MovieDTO> moviedto = moviedao.selectMovieByReleaseDate(movieReleaseDate);
		
		
		return moviedto;
	}

	@Override
	public MovieDTO selectMovieByName(String movieName) throws SQLException{
		
		MovieDTO moviedto = moviedao.selectMovieByName(movieName);
	
	
		return moviedto;
	}
		
		
		
	

}
