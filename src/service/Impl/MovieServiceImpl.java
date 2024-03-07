package service.Impl;

import java.sql.SQLException;
import java.util.List;

import javax.security.sasl.SaslException;

import exception.InsertException;
import exception.SearchException;
import management.DAO.Impl.MovieDAOImpl;
import management.DAO.interfaces.MovieDAO;
import management.DTO.MovieDTO;
import service.MovieService;

public class MovieServiceImpl implements MovieService {
	MovieDAO moviedao = new MovieDAOImpl();

	@Override
	public int insertMovie(String movieName,int movieGenre, String movieDirecter, 
			String releaseDate, List<String> leadActor,List<String> supportActor) throws InsertException {
		
		int result = moviedao.insertMovie( movieName,movieGenre,  movieDirecter,  releaseDate, leadActor, supportActor);
		if(result == 0) throw new InsertException("영화 등록에 실패했습니다.");
		
		return result;
	}

	@Override
	public List<MovieDTO> selectMovieByGenre(String movieGenre) throws SearchException {
		List<MovieDTO> moviedto = moviedao.selectMovieByGenre(movieGenre);
		
		for(MovieDTO m : moviedto) {
			if(m == null) {
				throw new SearchException();
			}
		}
		
		return moviedto;
	}

	@Override
	public List<MovieDTO> selectMovieByDirecter(String movieDirecter) throws SearchException {
		List<MovieDTO> moviedto = moviedao.selectMovieByDirecter(movieDirecter);
		
		for(MovieDTO m : moviedto) {
			if(m == null) {
				throw new SearchException();
			}
		}
		
		return moviedto;
	}

	@Override
	public List<MovieDTO> selectMovieByReleaseDate(String movieReleaseDate) throws SearchException {
		List<MovieDTO> moviedto = moviedao.selectMovieByReleaseDate(movieReleaseDate);
		
		for(MovieDTO m : moviedto) {
			if(m == null) {
				throw new SearchException();
			}
		}
		
		return moviedto;
	}

	@Override
	public MovieDTO selectMovieByName(String movieName) throws SearchException {
		MovieDTO moviedto = moviedao.selectMovieByName(movieName);
		
		if(moviedto == null) {
			throw new SearchException();
		}
		
		return moviedto;
	}

}
